package com.tangxiaolv.surgeon;

import android.util.Log;

import com.surgeon.weaving.annotations.Replace;
import com.surgeon.weaving.annotations.ReplaceAfter;
import com.surgeon.weaving.annotations.ReplaceBefore;
import com.surgeon.weaving.core.TargetHandle;
import com.surgeon.weaving.core.interfaces.ISurgeon;

public class HotReplace2 implements ISurgeon {

    private final static String TAG = "HotReplace2";

    /**
     * Called before target function call
     *
     * @param target The function owner's object.
     */
    @ReplaceBefore(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    public void getTwoBefore(Object target) {
        Log.d(TAG, "getTwoBefore");
    }

    /**
     * Replace target function
     *
     * @param handle The function owner's object.
     * @return new result
     */
    @Replace(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    public String getTwo(TargetHandle handle) {
        return "getTwo from HotReplace2";
    }

    /**
     * Replace target override function
     *
     * @param handle {@link TargetHandle}
     * @param text   origin params
     * @return new result
     */
    @Replace(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo.text")
    public String getTwo(TargetHandle handle, String text/**origin params*/) {
        return "getTwo from remote";
    }

    /**
     * Replace target override function
     *
     * @param target The function owner's object.
     */
    @ReplaceAfter(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    public void getTwoAfter(Object target) {
        Log.d(TAG, "getTwoAfter");
    }

    /**
     * Replace target override function
     *
     * @param handle {@link TargetHandle}
     * @param text   origin params
     * @return new result
     */
    @Replace(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getThree.text")
    public String getThree(TargetHandle handle, String text) throws Throwable {
        String newText = text + "_hack!";
        //invoke origin function with new params
        return (String) handle.proceed(newText);
    }

    @Override
    public boolean singleton() {
        return false;
    }
}
