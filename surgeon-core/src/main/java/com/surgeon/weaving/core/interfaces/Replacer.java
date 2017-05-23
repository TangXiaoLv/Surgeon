package com.surgeon.weaving.core.interfaces;

public interface Replacer {
    void before(Object[] params);

    Object replace(Object[] params);

    void after(Object[] params);
}
