package com.example.mayank.ldqapp;

import android.app.Application;

/**
 * Created by Mayank on 24-12-2016.
 */

public class LDQApp extends Application {

    public static String uid;
    private static LDQApp singleton;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public static LDQApp getInstance(){

        return singleton;
    }
}
