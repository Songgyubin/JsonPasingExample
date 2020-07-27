package com.sizey.cardata;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase.getInstance(this);
    }
}
