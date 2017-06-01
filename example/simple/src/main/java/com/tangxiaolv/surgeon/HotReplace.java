package com.tangxiaolv.surgeon;

import android.view.View;
import android.widget.Toast;

import com.surgeon.weaving.annotations.Replace;
import com.surgeon.weaving.core.TargetHandle;
import com.surgeon.weaving.core.interfaces.ISurgeon;

public class HotReplace implements ISurgeon {

    /**
     * Replace target override function
     *
     * @param handle {@link TargetHandle}
     * @param view   origin params
     */
    @Replace(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "oneClick")
    public void showToast(TargetHandle handle, View view) {
        Toast.makeText(view.getContext(), "Hack！！！", Toast.LENGTH_SHORT).show();
    }

    @Replace(namespace = "com.tangxiaolv.sdk.MyLinearLayout", function = "onMeasure")
    public void onMeasure(TargetHandle handle, int widthMeasureSpec, int heightMeasureSpec) throws Throwable {
        int w_s = View.MeasureSpec.getSize(widthMeasureSpec);
        int w_m = View.MeasureSpec.getMode(widthMeasureSpec);
        int h_s = View.MeasureSpec.getSize(heightMeasureSpec);
        int h_m = View.MeasureSpec.getMode(heightMeasureSpec);
        handle.proceed(View.MeasureSpec.makeMeasureSpec(w_s / 2, w_m), heightMeasureSpec);
    }

    @Override
    public boolean singleton() {
        return true;
    }
}
