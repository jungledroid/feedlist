package com.example.root.ui;

import android.app.Application;
import android.util.DisplayMetrics;

/**
 * Created by root on 15-11-9.
 */
public class FeedApplication  extends Application{
    private DisplayMetrics mDisplayMetrics;
    private static FeedApplication mApplication;

    public static FeedApplication getInstance(){
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setScreenSize(DisplayMetrics displayMetrics){
        mDisplayMetrics = displayMetrics;
    }

    public int getDisplayWidth(){
        return mDisplayMetrics.widthPixels;
    }

    public int getDisplayHeight(){
        return mDisplayMetrics.heightPixels;
    }
}
