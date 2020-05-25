package com.linktrust.student.uidemo;

import android.app.Application;
import android.content.Context;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/8/15 11:31
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
