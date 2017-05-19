package com.tangxiaolv.sdk;

import com.surgeon.weaving.annotations.ReplaceAble;

public class StringFactory {

    @ReplaceAble(extra = "AAA")
    public String getA() {
        return "A";
    }

    @ReplaceAble()
    public String getB() {
        return "B";
    }

    @ReplaceAble()
    public String getC() {
        return "C";
    }

}
