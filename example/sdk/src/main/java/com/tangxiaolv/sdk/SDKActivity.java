package com.tangxiaolv.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.surgeon.weaving.annotations.Replace;

public class SDKActivity extends AppCompatActivity {
    boolean b = true;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                Log.d("MainActivity", "name = " + name);
                int n = attrs.getAttributeCount();
                for (int i = 0; i < n; i++) {
                    String attributeName = attrs.getAttributeName(i);
                    Log.d("MainActivity", attributeName + " , " + attrs.getAttributeValue(i));
                }

                Log.d("MainActivity", "------------------------------");
                View view = null;
                attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "aaa");
//                if ("com.tangxiaolv.surgeon.MyButton".equals(name)) {
                if (name.equals("Button")) {
                    AppCompatDelegate delegate = getDelegate();
                    view = delegate.createView(parent, name, context, attrs);
                    if (b) {
                        view = new MyButton(context, attrs);
                        ((MyButton) view).setText("拦截拉！！！");
                        b = false;
                    } else {
                        ((Button) view).setWidth(200);
                    }
                    System.out.println();
                }

                return view;
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (TextView) findViewById(R.id.content);
    }

    public void oneClick(View view) {
        content.setText("content");
    }

    public void twoClick(View view) {
    }

    public void threeClick(View view) {
    }
}
