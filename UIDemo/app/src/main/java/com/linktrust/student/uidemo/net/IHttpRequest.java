package com.linktrust.student.uidemo.net;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.net
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/26 10:54
 */
public interface IHttpRequest {

    //请求url
    void setUrl(String url);

    //请求参数
    void setData(byte[] data);

    //回调监听
    void setListener(CallBackListener listener);

    //发起请求
    void execute();
}
