package com.tangxiaolv.surgeon;

import com.surgeon.weaving.annotations.Replace;
import com.surgeon.weaving.core.interfaces.ISurgeon;

public class HotReplace2 implements ISurgeon {

    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getOne")
    public Object getOne(Object target) {
        return "getOne from HotReplace2";
    }

    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public Object getTwo(Object target) {
        return "getTwo from HotReplace2";
    }
}
