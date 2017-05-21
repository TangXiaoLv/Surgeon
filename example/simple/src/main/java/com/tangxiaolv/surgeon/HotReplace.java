package com.tangxiaolv.surgeon;

import android.view.View;
import android.widget.Toast;

import com.surgeon.weaving.annotations.Replace;
import com.surgeon.weaving.core.interfaces.ISurgeon;

public class HotReplace implements ISurgeon {

    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.oneClick")
    public void showToast(Object target, View view) {
        Toast.makeText(view.getContext(), "被拦截拉！！！", Toast.LENGTH_SHORT).show();
        int a = 1 / 0;
    }

//    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.oneClick")
//    public Object showToast(Object target, View view) {
//        Toast.makeText(view.getContext(), "被拦截拉！！！", Toast.LENGTH_SHORT).show();
//        return void.class;
//    }
}
