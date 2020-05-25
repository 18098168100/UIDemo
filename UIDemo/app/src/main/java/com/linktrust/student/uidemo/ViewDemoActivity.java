package com.linktrust.student.uidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.linktrust.student.uidemo.bean.MessageBean;

import org.greenrobot.eventbus.EventBus;

public class ViewDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_demo);
//        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new MessageBean("EventBus消息"));
//            }
//        });

    }
}
