package com.tangxiaolv.surgeon;

import com.surgeon.weaving.annotations.Replace;
import com.surgeon.weaving.core.interfaces.ISurgeon;

public class HotReplace implements ISurgeon {

    @Replace(ref = "com.tangxiaolv.surgeon.oneClick")
    public String getA(Object target) {
        return "s";
    }

    public String getB(Object target) {
        return "ss";
    }
}
