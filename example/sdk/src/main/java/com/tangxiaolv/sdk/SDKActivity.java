package com.tangxiaolv.sdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.surgeon.weaving.annotations.ReplaceAble;
import com.surgeon.weaving.core.Surgeon;
import com.surgeon.weaving.core.interfaces.Replacer;

import static android.support.v7.widget.AppCompatDrawableManager.get;

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
        Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", "Runtime result");
    }

    public void addRuntimeMethod(View view) {
        Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", new Replacer() {
            @Override
            public void before(Object[] params) {
                System.out.println();
            }

            @Override
            public Object replace(Object[] params) {
                Toast.makeText(SDKActivity.this, "Runtime Replace", Toast.LENGTH_SHORT).show();
                return "Runtime method";
            }

            @Override
            public void after(Object[] params) {
                System.out.println();
            }
        });
    }

    @ReplaceAble
    public void oneClick(View view) {
        content.setText(getOne());
    }

    public void twoClick(View view) {
        content.setText(getTwo());
    }

    public void threeClick(View view) {
        content.setText(getThree(((Button) view).getText().toString()));
    }

    @ReplaceAble
    private String getOne() {
        return "ONE";
    }

    @ReplaceAble
    private final static String getTwo() {
        return "TWO";
    }

    @ReplaceAble
    private String getThree() {
        return "THREE";
    }

    @ReplaceAble(extra = "text")
    private String getThree(String text) {
        return text;
    }
}
