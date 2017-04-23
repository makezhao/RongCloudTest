package com.makezhao.rongcloudtest;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 17/4/23.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
    }
}