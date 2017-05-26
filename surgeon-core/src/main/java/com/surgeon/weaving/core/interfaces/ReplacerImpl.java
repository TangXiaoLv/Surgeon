package com.surgeon.weaving.core.interfaces;

/**
 * The implements of {@link Replacer}
 *
 * @param <T> The return value of {@link Replacer#replace(Object[])}
 */
public class ReplacerImpl<T> implements Replacer<T> {

    @Override
    public void before(Object[] params) {
        //nothing
    }

    @Override
    public T replace(Object[] params) {
        return null;
    }

    @Override
    public void after(Object[] params) {
        //nothing
    }
}
