package com.surgeon.weaving.core;

class ResultWapper {

    private boolean replacer;
    private Object result;

    ResultWapper(Object result, boolean replacer) {
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
