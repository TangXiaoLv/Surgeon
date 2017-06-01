package com.surgeon.weaving.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Used in AspectJ(AOP Framework for Java).It's meaning is what function should be weaving.
 */
@Retention(RUNTIME)
@Target({METHOD})
public @interface ReplaceAble {
    /**
     * The namespace of target function.
     */
    String namespace();

    /**
     * Function name
     */
    String function();
}
