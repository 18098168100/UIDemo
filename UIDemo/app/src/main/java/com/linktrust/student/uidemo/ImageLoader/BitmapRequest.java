package com.linktrust.student.uidemo.ImageLoader;

import android.content.Context;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.ImageLoader
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/22 15:16
 *
 * Glide.with(this)
 *           .load(url)
 *           .loading(R.drawable.loading)
 *           .listener(new RequestListener())
 *           .into(imageView);
 */
public class BitmapRequest {
    //图片空间
    private SoftReference<ImageView> imageView;

    //图片地址
    private String url;

    //占位图
    private int resID;

    //回调对象
    private RequestListener listener;

    //图片标识
    private String urlMd5;

    private Context context;

    public BitmapRequest(Context context){
        this.context = context;
    }

    public BitmapRequest load(String url){
        this.url = url;
        this.urlMd5 = MD5Utils.md5Encrypt32Upper(url);
        return this;//链式调用
    }

    public BitmapRequest loading(int resID){
        this.resID = resID;
        return this;
    }

    public BitmapRequest listener(RequestListener listener){
        this.listener = listener;
        return this;
    }

    public void into(ImageView imageView){
        imageView.setTag(this.urlMd5);
        this.imageView = new SoftReference<>(imageView);
        //将图片请求添加到队列中
        RequestManager.getInstance().addBitmapRequest(this);
    }

    public ImageView getImageView() {
        return imageView.get();
    }

    public String getUrl() {
        return url;
    }

    public int getResID() {
        return resID;
    }

    public RequestListener getListener() {
        return listener;
    }

    public String getUrlMd5() {
        return urlMd5;
    }

    public Context getContext() {
        return context;
    }
}
