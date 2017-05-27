package com.tangxiaolv.surgeon;

import android.util.Log;

import com.surgeon.weaving.annotations.Replace;
import com.surgeon.weaving.annotations.ReplaceAfter;
import com.surgeon.weaving.annotations.ReplaceBefore;
import com.surgeon.weaving.core.TargetHandle;
import com.surgeon.weaving.core.interfaces.ISurgeon;

import static android.content.ContentValues.TAG;

public class HotReplace2 implements ISurgeon {

    private final static String TAG = "HotReplace2";

    /**
     * Called before target function call
     *
     * @param target The function owner's object.
     */
    @ReplaceBefore(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public void getTwoBefore(Object target) {
        Log.d(TAG, "getTwoBefore");
    }

    /**
     * Replace target function
     *
     * @param handle The function owner's object.
     * @return new result
     */
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
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
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo", extra = "text")
    public String getTwo(TargetHandle handle, String text/**origin params*/) {
        return "getTwo from remote";
    }

    /**
     * Replace target override function
     *
     * @param target The function owner's object.
     */
    @ReplaceAfter(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
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
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getThree", extra = "text")
    public String getThree(TargetHandle handle, String text) throws Throwable {
        String newText = text + "_hack!";
        //invoke origin method with new params
        return (String) handle.proceed(newText);
    }
}
