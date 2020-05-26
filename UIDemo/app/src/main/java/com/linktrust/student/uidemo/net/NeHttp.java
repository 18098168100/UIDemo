package com.linktrust.student.uidemo.net;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.net
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/26 15:14
 */
public class NeHttp {
    public static <T,M> void sendJsonRequest(T requestData,String url,Class<M> response,
                                             IJsonDataTransforListener listener){
        IHttpRequest httpRequest = new JsonHttpRequest();
        CallBackListener callBackListener = new JsonCallBackListener<>(response,listener);
        HttpTask ht = new HttpTask(url,requestData,httpRequest,callBackListener);
        ThreadPoolManager.getInstance().addTask(ht);

    }
}
