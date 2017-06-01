package com.surgeon.weaving.core.interfaces;

/**
 * The callback of original function called.
 *
 * @param <T> The return value of {@link Replacer#replace(Object[])}
 */
public interface Replacer<T> {
    /**
     * Called before original function called.
     *
     * @param params The original function input params.
     */
    void before(Object[] params);

    /**
     * Replace original function.
     *
     * @param params The original function input params.
     * @return new return result.
     */
    T replace(Object[] params);

    /**
     * Called after original function called.
     *
     * @param params The original function input params.
     */
    void after(Object[] params);
}
