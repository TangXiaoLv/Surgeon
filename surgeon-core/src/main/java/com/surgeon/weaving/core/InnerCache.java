package com.surgeon.weaving.core;

import com.surgeon.weaving.core.interfaces.IMaster;
import com.surgeon.weaving.core.interfaces.Continue;

import java.util.HashMap;
import java.util.Map;

class InnerCache {

    private final Map<String, IMaster> masterCache = new HashMap<>();
    private final Map<Class, Object> ownerCache = new HashMap<>();
    private final Map<String, ResultWapper> resultWapperCache = new HashMap<>();

    private static class Lazy {
        static InnerCache sInnerCache = new InnerCache();
    }

    static InnerCache getInstance() {
        return InnerCache.Lazy.sInnerCache;
    }

    synchronized void putMaster(String key, IMaster master) {
        if (key != null && master != null && masterCache.get(key) == null) {
            masterCache.put(key, master);
        }
    }

    synchronized IMaster getMaster(String key) {
        if (key != null) {
            return masterCache.get(key);
        }
        return null;
    }

    synchronized void putMethodOwner(Class key, Object target) {
        if (key != null && target != null && ownerCache.get(key) == null) {
            ownerCache.put(key, target);
        }
    }

    synchronized Object getMethodOwner(Class key) {
        if (key != null) {
            return ownerCache.get(key);
        }
        return null;
    }

    synchronized void addResultWapper(String key, ResultWapper value) {
        if (key != null) {
            resultWapperCache.put(key, value);
        }
    }

    synchronized Object popResultWapper(String key) {
        if (resultWapperCache.containsKey(key)) {
            return resultWapperCache.remove(key);
        }
        return Continue.class;
    }
}
