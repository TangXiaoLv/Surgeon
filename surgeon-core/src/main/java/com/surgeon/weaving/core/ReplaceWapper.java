package com.surgeon.weaving.core;

class ReplaceWapper {

    private boolean replacer;
    private Object result;

    ReplaceWapper(Object result, boolean replacer) {
        this.result = result;
        this.replacer = replacer;
    }

    boolean isReplacer() {
        return replacer;
    }

    Object getResult() {
        return result;
    }
}
