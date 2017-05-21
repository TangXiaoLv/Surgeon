package com.surgeon.weaving.core;

import com.surgeon.weaving.core.interfaces.Replacer;

public class Surgeon {

    public static void replace(String path, Object result) {
        InnerCache.getInstance().addResultWapper(path, new ResultWapper(result, false));
    }

    public static void replace(String path, Replacer replacer) {
        InnerCache.getInstance().addResultWapper(path, new ResultWapper(replacer, true));
    }
}
