package com.linktrust.student.uidemo.net;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.net
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/26 14:06
 */
public class HttpTask<T> implements Runnable, Delayed {
    private IHttpRequest httpRequest;

    public HttpTask(String url,T requestData, IHttpRequest httpRequest,CallBackListener listener){
        this.httpRequest = httpRequest;
        httpRequest.setUrl(url);
        String content = JSON.toJSONString(requestData);
        try {
            httpRequest.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpRequest.setListener(listener);

    }
    @Override
    public void run() {
        try {
            httpRequest.execute();
        }catch (Exception o){
            ThreadPoolManager.getInstance().addDelayQueue(this);
        }

    }

    private long delayTime;
    private int retryCount;
    @Override
    public long getDelay(@NonNull TimeUnit unit) {
        return unit.convert(this.delayTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NonNull Delayed o) {
        return 0;
    }

    public IHttpRequest getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(IHttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime + System.currentTimeMillis();
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}
