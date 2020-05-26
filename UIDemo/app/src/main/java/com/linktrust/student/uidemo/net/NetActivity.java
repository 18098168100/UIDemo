package com.linktrust.student.uidemo.net;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.linktrust.student.uidemo.R;

public class NetActivity extends AppCompatActivity {

    //private String url = "http://v.juhe.cn/historyWeather/citys?provice_id=2&key=bb52107206585ab074f5e59a8c73875b";
    private String url = "http://v.juhe.cn/?provice_id=2&key=bb52107206585ab074f5e59a8c73875b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        NeHttp.sendJsonRequest(null, url, ResponseClass.class, new IJsonDataTransforListener() {
            @Override
            public void onSuccess(Object o) {
                Log.e("haohai","："+o.toString());
            }
        });
    }
}
