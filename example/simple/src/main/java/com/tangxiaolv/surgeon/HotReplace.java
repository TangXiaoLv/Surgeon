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
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.oneClick")
    public void showToast(TargetHandle handle, View view) {
        Toast.makeText(view.getContext(), "Hack！！！", Toast.LENGTH_SHORT).show();
    }
}
