package com.linktrust.student.uidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

//alt + enter  单元测试
public class SecondActivity extends AppCompatActivity {
    private String TAG = "SecondActivity";
    String url = "http://wwww.baidu.com";
    //String BASE_URL = "https://study.163.com/course/courseLearn.htm?courseId=1209333922#/learn/live?lessonId=1279123805&courseId=1209333922";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                .build();
//
//        Hot hot = retrofit.create(Hot.class);
//        Observer observer = new Observer() {
//
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e("haohai",">>>>>>>>>onError");
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Object o) {
//                Log.e("haohai",">>>>>>>>>onNext"+o.toString());
//
//            }
//        };
//
//
//        Observable observable = Observable.create(new ObservableOnSubscribe() {
//            @Override
//            public void subscribe(ObservableEmitter e) throws Exception {
//                e.onNext("123");
//            }
//        });
//
//        observable.subscribe(observer);
        request();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

    }

    //异步
    private synchronized void request(){
        //创建okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建Request对象
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        //创建Call对象
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }


    //同步
    private void syncRequest(){
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    Log.d(TAG, "run: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void HttpUrlConnection(){
        String str = "https://www.baidu.com/";
        URL url = null;
        try {
            url = new URL(str);
            //得到connection对象。
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //连接
            connection.connect();
            //得到响应码
            int responseCode = connection.getResponseCode();

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retrofit() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .build();
        Service s = retrofit.create(Service.class);
        Call call = (Call) s.listRepos();
        call.execute();

    }
}
