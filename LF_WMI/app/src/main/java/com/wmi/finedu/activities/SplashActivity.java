package com.wmi.finedu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wmi.finedu.R;

public class SplashActivity extends AppCompatActivity {

    private boolean mIsFinish = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View main = findViewById(R.id.ln_main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.sendEmptyMessage(0);
            }
        });
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(!mIsFinish) {
                mIsFinish = true;
                finish();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
            super.handleMessage(msg);
        }
    };
}
