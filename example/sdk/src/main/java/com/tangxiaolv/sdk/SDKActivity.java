package com.tangxiaolv.sdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.surgeon.weaving.annotations.ReplaceAble;
import com.surgeon.weaving.core.Surgeon;
import com.surgeon.weaving.core.interfaces.ReplacerImpl;

@SuppressWarnings("all")
public class SDKActivity extends AppCompatActivity {
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdk);
        content = (TextView) findViewById(R.id.content);
    }

    public void addRuntimeResult(View view) {
        //replace return value for target function
        Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", "Runtime result");
    }

    public void addRuntimeMethod(View view) {
        Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", new ReplacerImpl<String>() {
            //params[0] = function owner's object,The other is origin params
            @Override
            public void before(Object[] params) {
                super.before(params);
            }

            //params[0] = TargetHandle,The other is origin params
            @Override
            public String replace(Object[] params) {
                return super.replace(params);
            }

            //params[0] = function owner's object,The other is origin params
            @Override
            public void after(Object[] params) {
                super.after(params);
            }
        });
    }

    @ReplaceAble
    public void oneClick(View view) {
        content.setText(getOne());
    }

    public void twoClick(View view) {
        String two = getTwo();
        content.setText(two == null ? "null" : two);
    }

    public void threeClick(View view) {
        content.setText(getThree("Text"));
    }

    @ReplaceAble
    private String getOne() {
        return "ONE";
    }

    @ReplaceAble
    private final static String getTwo() {
        return "TWO";
    }

    @ReplaceAble(extra = "text")
    private String getTwo(String text) {
        return text;
    }

    @ReplaceAble(extra = "text")
    private String getThree(String text) {
        return "getThree_" + text;
    }
}
