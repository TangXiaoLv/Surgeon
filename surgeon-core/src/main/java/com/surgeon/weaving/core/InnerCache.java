package com.surgeon.weaving.core;

import android.util.LruCache;

import com.surgeon.weaving.core.interfaces.IMaster;
import com.surgeon.weaving.core.interfaces.Continue;

import java.util.HashMap;
import java.util.Map;

class InnerCache {

    private final LruCache<String, IMaster> masterCache = new LruCache<>(20);
    private final Map<Class, Object> ownerCache = new HashMap<>();
    private final Map<String, ReplaceWapper> replaceWapperCache = new HashMap<>();

    private static class Lazy {
        static InnerCache sInnerCache = new InnerCache();
    }

    static InnerCache getInstance() {
        return InnerCache.Lazy.sInnerCache;
    }

    synchronized void putMaster(String ref, IMaster master) {
        if (ref != null && master != null && masterCache.get(ref) == null) {
            masterCache.put(ref, master);
        }
    }

    synchronized IMaster getMaster(String key) {
        if (key != null) {
            return masterCache.get(key);
        }
        return null;
    }

    synchronized void putMethodOwner(Class owner, Object ownerInstance) {
        if (owner != null && ownerInstance != null && ownerCache.get(owner) == null) {
            ownerCache.put(owner, ownerInstance);
        }
    }

    synchronized Object getMethodOwner(Class owner) {
        if (owner != null) {
            return ownerCache.get(owner);
        }
        return null;
    }

    synchronized void addReplaceWapper(String ref, ReplaceWapper wapper) {
        if (ref != null) {
            replaceWapperCache.put(ref, wapper);
        }
    }

    synchronized Object popReplaceWapper(String ref) {
        if (replaceWapperCache.containsKey(ref)) {
            return replaceWapperCache.remove(ref);
        }
        return Continue.class;
    }

    synchronized Object getReplaceWapper(String ref) {
        if (replaceWapperCache.containsKey(ref)) {
            return replaceWapperCache.get(ref);
        }
        return Continue.class;
    }
}
