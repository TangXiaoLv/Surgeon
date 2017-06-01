package com.surgeon.weaving.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Replace new function when target function called.
 */
@Retention(CLASS)
@Target({METHOD})
public @interface Replace {
    /**
     * The target function namespace.
     */
    String namespace();

    /**
     * Function name
     */
    String function();
}
