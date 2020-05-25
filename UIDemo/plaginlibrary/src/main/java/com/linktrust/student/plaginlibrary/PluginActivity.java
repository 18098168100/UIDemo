package com.linktrust.student.plaginlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.plaginlibrary
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/7/12 16:17
 */
public class PluginActivity extends Activity implements IPlugin {

    /**
     * @param activity  定义插件的上下文
     */
    private Activity mProxyActivity;

    private int mFrom = FROM_INTERNAL;

    @Override
    public void attach(Activity activity) {
        mProxyActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            mFrom = savedInstanceState.getInt("FROM");
        }

        if (mFrom == FROM_INTERNAL){
            super.onCreate(savedInstanceState);
            mProxyActivity = this;
        }

    }

    @Override
    public void setContentView(int layoutResID) {
        if (mFrom == FROM_INTERNAL){
            super.setContentView(layoutResID);
        }else {
            mProxyActivity.setContentView(layoutResID);
        }

    }

    @Override
    public void onStart() {
        if (mFrom == FROM_INTERNAL){
            super.onStart();
        }
    }

    @Override
    public void onRestart() {
        if (mFrom == FROM_INTERNAL){
            super.onRestart();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mFrom == FROM_INTERNAL){
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onResume() {
        if (mFrom == FROM_INTERNAL){
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mFrom == FROM_INTERNAL){
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (mFrom == FROM_INTERNAL){
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (mFrom == FROM_INTERNAL){
            super.onDestroy();
        }
    }
}
