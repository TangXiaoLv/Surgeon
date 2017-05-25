package com.surgeon.weaving.core;

import com.surgeon.weaving.core.interfaces.Replacer;

public class Surgeon {

    /**
     * Runtime to replace original method's result.
     *
     * @param ref    The original method reference.
     * @param result New result.
     */
    public static void replace(String ref, Object result) {
        InnerCache.getInstance().addReplaceWapper(ref, new ReplaceWapper(result, false));
    }

    /**
     * Runtime to replace original method.
     *
     * @param ref      The original method reference.
     * @param replacer New method.
     */
    public static void replace(String ref, Replacer replacer) {
        InnerCache.getInstance().addReplaceWapper(ref, new ReplaceWapper(replacer, replacer != null));
    }
}
