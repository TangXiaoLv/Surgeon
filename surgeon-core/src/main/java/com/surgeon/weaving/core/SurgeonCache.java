package com.surgeon.weaving.core;

import com.surgeon.weaving.core.interfaces.IMaster;

import java.util.HashMap;
import java.util.Map;

public class SurgeonCache {

    private final Map<String, IMaster> masterCache = new HashMap<>();
    private final Map<IMaster, Object> ownerCache = new HashMap<>();

    private static class Lazy {
        static SurgeonCache sSurgeonCache = new SurgeonCache();
    }

    static SurgeonCache getInstance() {
        return SurgeonCache.Lazy.sSurgeonCache;
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

    synchronized void putTarget(IMaster key, Object target) {
        if (key != null && target != null && ownerCache.get(key) == null) {
            ownerCache.put(key, target);
        }
    }

    synchronized Object getOwner(IMaster key) {
        if (key != null) {
            return ownerCache.get(key);
        }
        return null;
    }
}
