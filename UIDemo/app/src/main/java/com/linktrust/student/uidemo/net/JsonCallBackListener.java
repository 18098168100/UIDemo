package com.linktrust.student.uidemo.net;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.net
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/26 13:43
 */
public class JsonCallBackListener<T> implements CallBackListener {

    private Class<T> responseClass;

    private IJsonDataTransforListener listener;

    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonCallBackListener(Class<T> responseClass, IJsonDataTransforListener listener) {
        this.responseClass = responseClass;
        this.listener = listener;

    }

    @Override
    public void onsuccess(InputStream inputStream) {
        //将InputStream转化为String
        String response = getContent(inputStream);
        //将String转化为class对象
        final T clazz = JSON.parseObject(response, responseClass);
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess(clazz);
            }
        });


    }

    private String getContent(InputStream inputStream) {

        StringBuilder sb = new StringBuilder();
        String line = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(line);
        }
        String str = sb.toString();
        return str;

    }

    @Override
    public void onFailure() {

    }
}
