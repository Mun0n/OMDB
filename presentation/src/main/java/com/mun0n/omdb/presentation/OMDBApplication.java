package com.mun0n.omdb.presentation;

import android.app.Application;

public class OMDBApplication extends Application {
    
    private static OMDBApplication sInstance;
    
    public static OMDBApplication getInstance() {
        return sInstance;
    }
    
    public static void setInstance(OMDBApplication omdbApplication) {
        sInstance = omdbApplication;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        
    }
}
