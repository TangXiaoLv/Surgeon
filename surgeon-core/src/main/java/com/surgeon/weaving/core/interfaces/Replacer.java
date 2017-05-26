package com.surgeon.weaving.core.interfaces;

/**
 * The callback of original method called.
 *
 * @param <T> The return value of {@link Replacer#replace(Object[])}
 */
public interface Replacer<T> {
    /**
     * Called before original method called.
     *
     * @param params The original method input params.
     */
    void before(Object[] params);

    /**
     * Replace original method.
     *
     * @param params The original method input params.
     * @return new return result.
     */
    T replace(Object[] params);

    /**
     * Called after original method called.
     *
     * @param params The original method input params.
     */
    void after(Object[] params);
}
