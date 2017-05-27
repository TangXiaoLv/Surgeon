package com.surgeon.weaving.core;

import com.surgeon.weaving.annotations.ReplaceAble;
import com.surgeon.weaving.core.interfaces.Continue;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.HashMap;

import static com.surgeon.weaving.core.ASPConstant.AFTER;
import static com.surgeon.weaving.core.ASPConstant.BEFORE;
import static com.surgeon.weaving.core.ASPConstant.EMPTY;

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

    //any method with ReplaceAble
    @Pointcut("execution(@com.surgeon.weaving.annotations.ReplaceAble * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    @Around("method()")
    public Object replacedIfNeeded(ProceedingJoinPoint jPoint) throws Throwable {
        String[] pair = parseNamespaceAndMethodName(jPoint);
        String namespace = pair[0];
        String fullName = pair[1];

        //invoke before method
        MasterFinder.getInstance().findAndInvoke(namespace, BEFORE, fullName, jPoint.getThis(), jPoint.getArgs());
        //invoke method
        Object result = MasterFinder.getInstance().findAndInvoke(namespace, EMPTY, fullName,
                new TargetHandle(jPoint), jPoint.getArgs());
        return result != Continue.class ? result : jPoint.proceed();
    }

    @After("method()")
    public void afterIfNeeded(JoinPoint jPoint) throws Throwable {
        String[] pair = parseNamespaceAndMethodName(jPoint);
        String namespace = pair[0];
        String fullName = pair[1];

        //invoke after method
        MasterFinder.getInstance().findAndInvoke(namespace, AFTER, fullName, jPoint.getThis(), jPoint.getArgs());
    }

    private String[] parseNamespaceAndMethodName(JoinPoint jPoint) {
        MethodSignature signature = (MethodSignature) jPoint.getSignature();
        String namespace = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        ReplaceAble replaceAble = signature.getMethod().getAnnotation(ReplaceAble.class);
        String extra = replaceAble.extra();
        String fullName = methodName + (extra.length() == 0 ? "" : "." + extra);
        return new String[]{namespace, fullName};
    }
}
