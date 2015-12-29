package com.firengy.lesson1;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity implements Runnable{

    public static final int ACTION_START = 0;
    private ImageView splash1;
    private ImageView splash2;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash1 = (ImageView) findViewById(R.id.splash1);
        //splash2 = (ImageView) findViewById(R.id.splash2);

        new Thread(this).start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case ACTION_START:
                        splash1.setVisibility(View.VISIBLE);
                        break;
                }
            }
        };
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            Message msg = new Message();
            msg.what = ACTION_START;
            handler.sendMessage(msg);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
