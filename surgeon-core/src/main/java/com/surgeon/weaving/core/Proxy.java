package com.surgeon.weaving.core;

import java.lang.reflect.Method;

public class Proxy {

    private Proxy() {
    }

    private static class Lazy {
        static Proxy sRouterHelper = new Proxy();
    }

    static Proxy getInstance() {
        return Lazy.sRouterHelper;
    }

    public Object findAndInvoke(Object target, String signature, Object[] args) {
        return void.class;
    }

    public Object invoke(Method method, Object[] args) {
        Object invoke = null;
        try {
            invoke = method.invoke(null, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoke;

    }
}
