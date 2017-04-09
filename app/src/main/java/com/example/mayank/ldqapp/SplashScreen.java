package com.example.mayank.ldqapp;

/**
 * Created by Mayank on 16-12-2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

    private static int splash_timer=3000;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(intent);

                finish();
            }
        },splash_timer);
    }
}
