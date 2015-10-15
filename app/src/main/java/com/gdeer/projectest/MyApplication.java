package com.gdeer.projectest;

import android.app.Application;
import android.content.Context;

/**
 * Created by Gdeer on 2015/10/12.
 */
public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }
}