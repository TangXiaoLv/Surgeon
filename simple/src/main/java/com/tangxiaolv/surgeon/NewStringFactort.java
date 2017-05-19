package com.tangxiaolv.surgeon;

import com.surgeon.weaving.annotations.Replace;

public class NewStringFactort {

    @Replace(ref = "com.tangxiaolv.sdk.StringFactory.getA",extra = "AAA")
    public String getA(Object target) {
        return "s";
    }

    @Replace(ref = "com.tangxiaolv.sdk.StringFactory2.getA",extra = "BBB")
    public String getB(Object target) {
        return "ss";
    }
}
