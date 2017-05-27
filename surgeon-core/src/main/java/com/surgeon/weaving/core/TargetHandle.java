package com.surgeon.weaving.core;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * The execute Join Point handle.
 */
public class TargetHandle {

    private Object target;
    private ProceedingJoinPoint origin;

    TargetHandle(ProceedingJoinPoint origin) {
        if (origin != null) {
            this.origin = origin;
            this.target = origin.getThis();
        }
    }

    /**
     * Invoke target function with new params.
     *
     * @param params input params
     * @return new result
     */
    public Object proceed(Object... params) throws Throwable {
        if (origin != null) {
            return origin.proceed(params);
        }
        return null;
    }

    /**
     * Get function owner object.
     *
     * @return The target function owner.
     */
    public Object getTarget() {
        return target;
    }
}
