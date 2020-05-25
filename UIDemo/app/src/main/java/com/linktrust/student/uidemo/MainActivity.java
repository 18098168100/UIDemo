package com.linktrust.student.uidemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linktrust.student.uidemo.arouter.ARouterActivity;
import com.linktrust.student.uidemo.bean.MessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Thread.UncaughtExceptionHandler{
    private MyScrollView scrollView;
    private TextView title_tv1;
    private TextView title_tv2;
    private SwipeRefreshLayout swiperereshlayout;
    private boolean isLogin = true;
    private View view1, view2;
    private LinearLayout ll_contain;
    private Class c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
        long time = System.currentTimeMillis();//获取当前系统时间
        Date date = new Date();
        date.setTime(time);
        Log.e("haohai 现在时间:", time+"");
        Log.e("haohai 现在时间:", simpleDateFormat.format(date));


        Date date2 = new Date();
        long time2 = 1587865176634L;
        date2.setTime(time2);
        Log.e("haohai 原来时间:", 1587865176634L+"");
        Log.e("haohai 原来时间:", simpleDateFormat.format(date2));

        Map hashMap = new HashMap();
        hashMap.put("","null");
        for (Object value : hashMap.values()) {
            Log.e("haohai666>>>", value+"");
        }
        HashSet hashSet = new HashSet();
        MessageBean messageBean1 = new MessageBean("小明","1");
        MessageBean messageBean2 = new MessageBean("小明","1");
        hashSet.add(messageBean1);
        hashSet.add(messageBean2);
        Iterator<MessageBean> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            MessageBean student = iterator.next();
            //Log.e("haohai666", student.toString());
        }
            //EventBus.getDefault().register(this);
        scrollView = findViewById(R.id.sv);
        //底部头文件
        view1 = findViewById(R.id.title_layout1);
        //上面头文件
        view2 = findViewById(R.id.title_layout2);
        title_tv1 = view1.findViewById(R.id.title_tv);
        title_tv2 = view1.findViewById(R.id.proxy_btn);
        ll_contain = findViewById(R.id.ll_contain);
        swiperereshlayout = findViewById(R.id.swiperereshlayout);
        title_tv1.setOnClickListener(this);
        title_tv2.setOnClickListener(this);
        findViewById(R.id.jump_secondActivity).setOnClickListener(this);
        findViewById(R.id.jump_ProxyActivity).setOnClickListener(this);
        findViewById(R.id.send_message).setOnClickListener(this);
        findViewById(R.id.jump_Scroolview).setOnClickListener(this);
        findViewById(R.id.jump_goole_View).setOnClickListener(this);
        findViewById(R.id.jump_Rxlifecycle).setOnClickListener(this);
        findViewById(R.id.jump_plugin_activity).setOnClickListener(this);
        findViewById(R.id.jump_arouter_activity).setOnClickListener(this);
        findViewById(R.id.jump_imageLoader_activity).setOnClickListener(this);
        scrollView.setScroll_Y(0);
        scrollView.setLl_contain(ll_contain);
        //初始化（滑动为0）时，隐藏底部头文件
        view1.setVisibility(View.INVISIBLE);

        //登录状态刷新有效，否则刷新无效
        if (isLogin) {
            swiperereshlayout.setEnabled(true);
            scrollView.setLogin(true);
        } else {
            swiperereshlayout.setEnabled(false);
            scrollView.setLogin(false);
        }

        //监听scrollView滑动的高度
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollView.setScroll_Y(scrollY);
                if (scrollY == 0) {
                    view1.setVisibility(View.INVISIBLE);
                    view2.setVisibility(View.VISIBLE);
                } else {
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.INVISIBLE);
                }
            }
        });

        //给swipeRefreshLayout绑定刷新监听
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    //如果登录，刷新数据
                    //设置2秒的时间来执行以下事件
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            swiperereshlayout.setRefreshing(false);
//                            try {
//                                Thread.sleep(20000);
//                                Log.e("haohai","...........");
//                                title_tv1.setText("hello word");
//                                title_tv2.setText("hello word");
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
                        }
                    }, 2000);
                }
        });

        //测量头View的高度
        ViewTreeObserver vto = view2.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height_view = view2.getHeight();
                scrollView.setHeight_view(height_view);

            }
        });

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("haohai",">>>>>>>>>>>>>>>+onStart()");
        c = SecondActivity.class;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("haohai",">>>>>>>>>>>>>>>+onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("haohai",">>>>>>>>>>>>>>>+onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("haohai",">>>>>>>>>>>>>>>+onStop()");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_tv:
                break;
            case R.id.jump_ProxyActivity:
                startActivity(new Intent(this, UIActivity.class));

                break;
            case R.id.jump_secondActivity:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.send_message:
                EventBus.getDefault().post(new MessageBean("发送粘性事件"));
                break;
            case R.id.jump_Scroolview:
                startActivity(new Intent(this, ThirdActivity.class));
                break;
            case R.id.jump_goole_View:
                startActivity(new Intent(this, GooleViewActivity.class));
                break;
            case R.id.jump_Rxlifecycle:
                startActivity(new Intent(this, RxLifeCycleActivity.class));
                break;
            case R.id.jump_plugin_activity:
                startActivity(new Intent(this, PluginDemoActivity.class));
                break;
            case R.id.jump_arouter_activity:
//                Uri uri = Uri.parse("http://preview.hengshi.io/#/share/49AB8138/dashboard/2C454926");    //设置跳转的网站
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
                startActivity(new Intent(this,ARouterActivity.class));
            case R.id.jump_imageLoader_activity:
                startActivity(new Intent(this,ImageLoaderActivity.class));
                break;
            default:
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.e("haohai",">>>>>>>>>>>>>>>+onDestroy()");
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("haohai",">>>>>>>>"+e.getMessage());
    }
}
