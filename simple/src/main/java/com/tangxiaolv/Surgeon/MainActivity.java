package com.tangxiaolv.surgeon;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.surgeon.weaving.annotations.Replace;
import com.tangxiaolv.sdk.StringFactory;

import org.xmlpull.v1.XmlPullParserException;

public class MainActivity extends AppCompatActivity {

    private TextView content;
    private StringFactory stringFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                Log.e("MainActivity", "name = " + name);
                int n = attrs.getAttributeCount();
                for (int i = 0; i < n; i++) {
                    String attributeName = attrs.getAttributeName(i);
                    Log.e("MainActivity", attributeName + " , " + attrs.getAttributeValue(i));
                    if ("com.tangxiaolv.surgeon.MyLinearLayout".equals(name) && "orientation".equals(attributeName)) {
                        try {
                            ((XmlResourceParser) attrs).setProperty(attributeName, 0);
                            Log.e("MainActivity", attributeName + " , " + attrs.getAttributeValue(i));
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Log.e("MainActivity", "------------------------------");
                return null;
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (TextView) findViewById(R.id.content);
        stringFactory = new StringFactory();
    }

    @Replace(ref = "com.tangxiaolv.surgeon.oneClick")
    public void oneClick(View view) {
        content.setText(stringFactory.getA());
    }

    @Replace(ref = "com.tangxiaolv.surgeon.twoClick")
    public void twoClick(View view) {
        content.setText(stringFactory.getB());
    }

    @Replace(ref = "com.tangxiaolv.surgeon.threeClick")
    public void threeClick(View view) {
        content.setText(stringFactory.getC());
        startActivity(new Intent(this, TwoActivity.class));
    }
}
