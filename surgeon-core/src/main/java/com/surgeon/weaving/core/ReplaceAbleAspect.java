package com.surgeon.weaving.core;

import com.surgeon.weaving.annotations.ReplaceAble;
import com.surgeon.weaving.core.interfaces.Continue;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Target interceptor.
 *
 * Singleton
 */
@Aspect
public class ReplaceAbleAspect {

    @Pointcut("within(@com.surgeon.weaving.annotations.ReplaceAble *)")
    public void withinAnnotatedClass() {
    }

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {
    }

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {
    }

    //any method with ReplaceAble
    @Pointcut("execution(@com.surgeon.weaving.annotations.ReplaceAble * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    //any constructor with ReplaceAble
    @Pointcut("execution(@com.surgeon.weaving.annotations.ReplaceAble *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {
    }

    @Around("method() || constructor()")
    public Object replacedIfNeeded(ProceedingJoinPoint jPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) jPoint.getSignature();
        String namespace = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        ReplaceAble replaceAble = signature.getMethod().getAnnotation(ReplaceAble.class);
        String extra = replaceAble.extra();
        String fullName = methodName + (extra.length() == 0 ? "" : "." + extra);
        Object result = MasterFinder.getInstance().findAndInvoke(namespace, fullName, jPoint.getThis(), jPoint.getArgs());
        return result != Continue.class ? result : jPoint.proceed();
    }
}
