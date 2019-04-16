package com.smile.livedatademo;

import android.app.Application;
import android.content.Context;

import com.smile.livedatademo.room.AppExecutors;

public class MyApplication extends Application {
    private static MyApplication myApplication;

    private static AppExecutors mAppExecutors;


    /**
     * 上下文
     */
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        context=getApplicationContext();
        mAppExecutors = new AppExecutors();

    }

    public static AppExecutors getAppExecutors(){
        return mAppExecutors;
    }





}
