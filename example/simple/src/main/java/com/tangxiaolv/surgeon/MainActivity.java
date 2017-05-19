package com.tangxiaolv.surgeon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tangxiaolv.sdk.SDKActivity;

public class MainActivity extends AppCompatActivity {

    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (TextView) findViewById(R.id.content);
    }

    public void oneClick(View view) {
    }

    public void twoClick(View view) {
    }

    public void threeClick(View view) {
        startActivity(new Intent(this, SDKActivity.class));
    }
}
