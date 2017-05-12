package com.surgeon.weaving.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

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

    @Pointcut("execution(@com.surgeon.weaving.annotations.ReplacedAble * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    @Pointcut("execution(@com.surgeon.weaving.annotations.ReplacedAble *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {
    }

    @Around("method() || constructor()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("----------------------");
        return joinPoint.proceed();
    }
}
