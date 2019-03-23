package com.example.jianji.Utils;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class BaseActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Bmob初始化
        Bmob.initialize(this, "de04c6d94ab157d81d673c9e071c99cd");
    }
}
