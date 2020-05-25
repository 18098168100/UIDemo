package com.linktrust.student.uidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.linktrust.student.uidemo.bean.MessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UIActivity extends AppCompatActivity {
private TextView receiv_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        receiv_msg = findViewById(R.id.receiv_msg);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UIActivity.this,ViewDemoActivity.class));
            }
        });
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMsg2(MessageBean msg){
        Log.e("haohai","UIActivity>>>>"+msg.getMsg());
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e("haohai",">>>>>>>>>>>>>>>>>>stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("haohai",">>>>>>>>>>>>>>>>>>onDestroy");

        EventBus.getDefault().unregister(this);
    }
}
