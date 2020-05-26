package com.linktrust.student.uidemo.net;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.net
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/26 13:38
 */
public class JsonHttpRequest implements IHttpRequest {

    private String url;

    private byte[] data;

    private CallBackListener listener;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public void setListener(CallBackListener listener) {
        this.listener = listener;
    }

    @Override
    public void execute() {
        //访问网络的具体操作
        URL url = null;
        //HttpURLConnection 是对 Socket的封装
        /**
         public HttpConnection(HttpConfiguration config, int connectTimeout) throws IOException {
         this.config = config;
         String hostName = config.getHostName();
         int hostPort = config.getHostPort();
         Proxy proxy = config.getProxy();
         if(proxy == null || proxy.type() == Proxy.Type.HTTP) {
         socket = new Socket();
         } else {
         socket = new Socket(proxy);
         }
         socket.connect(new InetSocketAddress(hostName, hostPort), connectTimeout);
         }
         */
        HttpURLConnection urlConnection = null;
        try {
            //创建Url对象
            url = new URL(this.url);
            //打开http连接
            urlConnection = (HttpURLConnection) url.openConnection();
            //设置超时
            urlConnection.setConnectTimeout(6000);
            //不使用缓存
            urlConnection.setUseCaches(false);
            //是成员函数，仅作用于当前函数，设置这个链接是否可以被重定向
            urlConnection.setInstanceFollowRedirects(true);
            //响应的超时时间
            urlConnection.setReadTimeout(3000);
            //设置这个链接是否可以写入数据
            urlConnection.setDoInput(true);
            //设置这个链接是否可以输出数据
            urlConnection.setDoOutput(true);
            //设置请求方式
            urlConnection.setRequestMethod("POST");
            //设置消息类型
            urlConnection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            //连接，从上述至此的配置必须要在connect之前完成，实际上他是建立了一个与服务器的TCP连接
            urlConnection.connect();
            /**-------------使用字节流发送数据-------------*/
            OutputStream out = urlConnection.getOutputStream();
            //缓冲字节流包装字节流
            BufferedOutputStream bos = new BufferedOutputStream(out);
            //将字节数组数据写到缓冲区中
            bos.write(data);
            //刷新缓冲区，发送数据
            bos.flush();
            out.close();
            bos.close();
            /**-------------使用字符流接受数据-------------*/
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream in = urlConnection.getInputStream();
                this.listener.onsuccess(in);
            }else {
                throw new RuntimeException("请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("请求失败");
        }finally {
            urlConnection.disconnect();
        }
    }
}
