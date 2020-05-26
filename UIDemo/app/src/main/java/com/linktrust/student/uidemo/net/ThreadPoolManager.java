package com.linktrust.student.uidemo.net;

import android.util.Log;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.net
 * 类描述:线程池管理类
 * 创建人:hh
 * 创建时间:2020/5/26 11:02
 */
public class ThreadPoolManager {

    public static ThreadPoolManager threadPoolManager = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return threadPoolManager;
    }

    //创建队列
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();
    //创建延时队列
    //DelayQueue：一个使用优先级队列实现的无界   阻塞队列
    private DelayQueue<HttpTask> mDelayQueue = new DelayQueue<>();

    public void addDelayQueue(HttpTask httpTask) {
        if (httpTask != null) {
            httpTask.setDelayTime(3000);
            mDelayQueue.offer(httpTask);
        }
    }

    //将异步任务添加到队列中
    public void addTask(Runnable runnable) {
        if (runnable != null) {
            try {
                mQueue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //创建线程池
    private ThreadPoolExecutor mThreadPoolExecutor;

    // ArrayBlockingQueue 线程池中维护的队列
    private ThreadPoolManager() {
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                addTask(r);
            }
        });
        mThreadPoolExecutor.execute(coreThread);
        mThreadPoolExecutor.execute(delayThread);
    }

    //创建核心线程，去队列中获取请求，提交到线程池处理
    public Runnable coreThread = new Runnable() {
        Runnable runnable = null;

        @Override
        public void run() {
            while (true) {
                try {

                    //获取队列中的请求
                    runnable = mQueue.take();
                    //将请求添加到线程池
                    mThreadPoolExecutor.execute(runnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public Runnable delayThread = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    //获取队列中的请求
                    HttpTask ht = mDelayQueue.take();
                    //将请求添加到线程池
                    if (ht.getRetryCount() < 3) {
                        mThreadPoolExecutor.execute(ht);
                        ht.setRetryCount(ht.getRetryCount() + 1);
                        Log.e("haohai", ">>>>>重试机制>>>>>" + ht.getRetryCount());
                    } else {
                        Log.e("haohai", ">>>>>重试次数过多>>>>>" + ht.getRetryCount());
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}
