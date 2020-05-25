package com.linktrust.student.uidemo.ImageLoader;

import android.util.Log;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.ImageLoader
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/25 10:47
 */
public class RequestManager {

    private static RequestManager requestManager = new RequestManager();

    //创建线程数组
    private BitmapDispather[] bitmapDispathers;

    //创建队列
    private LinkedBlockingQueue<BitmapRequest> requestQueue = new LinkedBlockingQueue();

    private RequestManager(){
        start();
    }

    public void start(){
        stop();
        startAllDispatcher();
    }

    //开启工作线程
    private void startAllDispatcher() {
        Log.e("haohai","开启工作线程");

        //获取手机单个应用最大的线程数
        int threadCount = Runtime.getRuntime().availableProcessors();
        Log.e("haohai","threadCount:>>>>>>>>"+threadCount);
        bitmapDispathers = new BitmapDispather[threadCount];
        for (int i = 0; i < threadCount; i++) {
            BitmapDispather bitmapDispather = new BitmapDispather(requestQueue);
            bitmapDispather.start();

            //将每个dispather放到数组中方便管理
            bitmapDispathers[i] = bitmapDispather;
        }
    }

    //停止所有工作线程
    public void stop(){
        Log.e("haohai","停止工作线程");
        if (bitmapDispathers != null && bitmapDispathers.length > 0){
            for (BitmapDispather bitmapDispather : bitmapDispathers) {
                if (!bitmapDispather.isInterrupted()){
                    bitmapDispather.interrupt();
                }
            }
        }
    }


    public static  RequestManager getInstance(){
        return requestManager;
    }

    //将图片请求添加到队列
    public void addBitmapRequest(BitmapRequest bitmapRequest){
        if (bitmapRequest == null){
            return;
        }

        if (!requestQueue.contains(bitmapRequest)){
            requestQueue.add(bitmapRequest);
        }
    }

}
