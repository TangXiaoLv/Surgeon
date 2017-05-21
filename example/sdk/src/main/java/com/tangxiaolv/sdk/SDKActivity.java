package com.tangxiaolv.sdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.surgeon.weaving.annotations.ReplaceAble;
import com.surgeon.weaving.core.Surgeon;
import com.surgeon.weaving.core.interfaces.Continue;
import com.surgeon.weaving.core.interfaces.Replacer;

@SuppressWarnings("all")
public class SDKActivity extends AppCompatActivity {
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdk);
        content = (TextView) findViewById(R.id.content);

    }

    public void runtimeResult(View view) {
        Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", "Runtime result");
    }

    public void runtimeMethod(View view) {
        Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", new Replacer() {
            @Override
            public Object invoke(Object[] params) {
                Toast.makeText(SDKActivity.this, "Runtime Replace", Toast.LENGTH_SHORT).show();
                return "Runtime method";
            }
        });
    }

    @ReplaceAble
    public void oneClick(View view) {
        content.setText(getOne(((Button) view).getText().toString()));
    }

    public void twoClick(View view) {
        content.setText(getOne());
    }

    public void threeClick(View view) {
        content.setText(getTwo());
    }

    @ReplaceAble(extra = "text")
    private String getOne(String text) {
        return text;
    }

    @ReplaceAble
    private String getOne() {
        return "ONE";
    }

    @ReplaceAble
    private String getTwo() {
        return "TWO";
    }
}
