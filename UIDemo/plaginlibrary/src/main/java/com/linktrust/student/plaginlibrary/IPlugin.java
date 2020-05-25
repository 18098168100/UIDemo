package com.linktrust.student.plaginlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.plaginlibrary
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/7/12 16:06
 */
public interface IPlugin {

    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL =1;

    void attach(Activity activity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

}
