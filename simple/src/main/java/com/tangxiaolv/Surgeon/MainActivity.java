package com.tangxiaolv.surgeon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void oneClick(View view) {
        Log.d("AOP","ONE");
    }

    public void twoClick(View view) {
        Log.d("AOP","TWO");
    }

    public void threeClick(View view) {
        Log.d("AOP","THREE");
    }
}
