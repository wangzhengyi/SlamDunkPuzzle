package com.android.slamdunk;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;


public class WelcomeActivity extends Activity {

    private Handler handler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);


        this.handler.postDelayed(mRunnable, 2000L);
    }

}
