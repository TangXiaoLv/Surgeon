package com.surgeon.weaving.core;

import com.surgeon.weaving.core.interfaces.Replacer;

public class Surgeon {

    /**
     * Runtime to replace original function's result.
     *
     * @param ref    namespace + "." + function
     * @param result New result.
     */
    public static void replace(String ref, Object result) {
        InnerCache.getInstance().addReplaceWapper(ref, new ReplaceWapper(result, false));
    }

    /**
     * Runtime to replace original function.
     *
     * @param ref      namespace + "." + function
     * @param replacer new function.
     */
    public static void replace(String ref, Replacer replacer) {
        InnerCache.getInstance().addReplaceWapper(ref, new ReplaceWapper(replacer, replacer != null));
    }
}
