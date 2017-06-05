package com.surgeon.weaving.core.interfaces;

import com.surgeon.weaving.core.TargetHandle;

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

    @SuppressWarnings("unchecked")
    @Override
    public T replace(Object[] params) throws Throwable {
        Object[] copy = new Object[params.length - 1];
        System.arraycopy(params, 1, copy, 0, params.length - 1);
        return (T) ((TargetHandle) params[0]).proceed(copy);
    }

    @Override
    public void after(Object[] params) {
        //nothing
    }
}
