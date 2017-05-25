package com.surgeon.weaving.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Used in AspectJ(AOP Framework for Java).It's meaning is what method should be weaving.
 */
@Retention(RUNTIME)
@Target({METHOD})
public @interface ReplaceAble {
    /**
     * Extra mark,Usually used in override.
     */
    String extra() default "";
}
