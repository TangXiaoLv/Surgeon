package com.tangxiaolv.surgeon;

import com.surgeon.weaving.annotations.Replace;
import com.surgeon.weaving.annotations.ReplaceAfter;
import com.surgeon.weaving.annotations.ReplaceBefore;
import com.surgeon.weaving.core.interfaces.ISurgeon;

public class HotReplace2 implements ISurgeon {

    @ReplaceBefore(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public void getTwoBefore(Object target) {
        System.out.println();
    }

    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public Object getTwo(Object target) {
        return "getTwo from HotReplace2";
    }

    @ReplaceAfter(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public void getTwoAfter(Object target) {
        System.out.println();
    }

    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getThree", extra = "text")
    public Object getThree(Object target, String text) {
        return "getThree from HotReplace2 text=" + text;
    }

    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getThree")
    public Object getThree(Object target) {
        return "getThree from HotReplace2";
    }
}
