package com.surgeon.weaving.core.interfaces;

/**
 * The callback of original method called.
 */
public interface Replacer {
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
    Object replace(Object[] params);

    /**
     * Called after original method called.
     *
     * @param params The original method input params.
     */
    void after(Object[] params);
}
