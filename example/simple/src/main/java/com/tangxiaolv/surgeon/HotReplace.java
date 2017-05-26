package com.tangxiaolv.surgeon;

import android.view.View;
import android.widget.Toast;

import com.surgeon.weaving.annotations.Replace;
import com.surgeon.weaving.core.interfaces.Continue;
import com.surgeon.weaving.core.interfaces.ISurgeon;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HotReplace implements ISurgeon {

    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.oneClick")
    public void showToast(Object target, View view) {
        Toast.makeText(view.getContext(), "被拦截拉！！！", Toast.LENGTH_SHORT).show();
    }
}
