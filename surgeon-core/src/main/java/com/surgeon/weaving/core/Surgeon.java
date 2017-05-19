package com.surgeon.weaving.core;

import com.surgeon.weaving.annotations.ReplaceAble;

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
public class Surgeon {

    @Pointcut("within(@com.surgeon.weaving.annotations.ReplacedAble *)")
    public void withinAnnotatedClass() {
    }

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {
    }

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {
    }

    //any method with ReplacedAble
    @Pointcut("execution(@com.surgeon.weaving.annotations.ReplacedAble * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    //any constructor with ReplacedAble
    @Pointcut("execution(@com.surgeon.weaving.annotations.ReplacedAble *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {
    }

    @Around("method() || constructor()")
    public Object replacedIfNeeded(ProceedingJoinPoint jPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) jPoint.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        ReplaceAble annotation = signature.getMethod().getAnnotation(ReplaceAble.class);
        String key = declaringTypeName + "." + methodName;
        Object result = Proxy.getInstance().findAndInvoke(jPoint.getThis(), key, jPoint.getArgs());
        if (result != void.class) {
            return result;
        }
        return jPoint.proceed();
    }
}
