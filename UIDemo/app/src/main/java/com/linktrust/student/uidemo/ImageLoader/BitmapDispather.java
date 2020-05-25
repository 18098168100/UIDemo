package com.linktrust.student.uidemo.ImageLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.ImageLoader
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/22 15:58
 */
public class BitmapDispather extends Thread {

    private Handler handler = new Handler(Looper.getMainLooper());

    //创建一个阻塞队列
    private LinkedBlockingQueue<BitmapRequest> requestQueue;

    private BitmapRequest bitmapRequest;

    public BitmapDispather(LinkedBlockingQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()){
            //从队列中获取队列请求
            try {
                bitmapRequest = requestQueue.take();
                Log.e("haohai","获取队列中的 request");
                //设置占位图片
                showLoadingImage(bitmapRequest);
                //加载图片
                Bitmap bitmap = findBitmap(bitmapRequest);
                //将图片显示在ImageView上
                showImageView(bitmapRequest, bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void showImageView(final BitmapRequest bitmapRequest, final Bitmap bitmap) {
        if (bitmap !=null && bitmapRequest.getImageView()!=null
             && bitmapRequest.getUrlMd5().equals(bitmapRequest.getImageView().getTag())){
            final ImageView imageView = bitmapRequest.getImageView();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    if (bitmapRequest.getListener() != null){
                        RequestListener listener = bitmapRequest.getListener();
                        listener.onSuccess(bitmap);
                    }
                }
            });
        }
    }

    private Bitmap findBitmap(BitmapRequest bitmapRequest) {
        Bitmap bitmap = downloadBitmap(bitmapRequest.getUrl());
        return bitmap;
    }

    private Bitmap downloadBitmap(String uri) {
        FileOutputStream fos = null;
        InputStream is = null;
        Bitmap bitmap = null;

        try {
            //创建一个Url对象
            URL url = new URL(uri);
            //然后使用HttpUrlConnection 通过URL去读数据
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //获取输入流
            is = conn.getInputStream();
            //将输入流转化为bitmap
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {

            //关闭输入流
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //关闭输出流
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bitmap;
    }


    private void showLoadingImage(BitmapRequest bitmapRequest) {
        if(bitmapRequest.getResID()>0 && bitmapRequest.getImageView() != null){
            final int resID = bitmapRequest.getResID();
            final ImageView imageView = bitmapRequest.getImageView();
            handler.post(new Runnable() {
                @Override
                public void run() {
                imageView.setImageResource(resID);
                }
            });

        }
    }
}
