package com.linktrust.student.uidemo;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/5/30 15:36
 */
public class MyScrollView extends NestedScrollView {
    //控件Y轴滑动的距离
    private int scroll_Y;
    //透明控件的高度
    private int height_view;
    //手指按下时Y轴的坐标
    private int mPosY = 0;
    //手指在Y轴的滑动距离
    private float distans_y;
    //用户是否登录
    private boolean isLogin;
    //防止多次启动登录
    private boolean isJump;
    //最大滑动距离
    private int MAX_Y = 400;
    //改变透明度控件
    private LinearLayout ll_contain;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getY() < (height_view - scroll_Y) || ev.getY() < (height_view - scroll_Y)) {
            return false;
        }

        int mCurPosY;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPosY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurPosY = (int) ev.getY();
                //向下滑动
                distans_y = mCurPosY - mPosY;
                if (distans_y > 0 && scroll_Y == 0 && !isLogin) {
                    if (distans_y < MAX_Y) {
                        //改变透明度
                        float p = distans_y / MAX_Y;
                        ll_contain.setAlpha(1 - p);
                    } else {
                        //跳转登录
                        if (isJump) break;
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        isJump = true;
                        getContext().startActivity(intent);
                            //设置100ms的时间来执行以下事件
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    ll_contain.setAlpha(1);
                                    distans_y = 0;
                                }
                            }, 100);

                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                ll_contain.setAlpha(1);
                distans_y = 0;
                isJump = false;
                break;
        }

        return super.onTouchEvent(ev);
    }

    public void setScroll_Y(int scroll_Y) {
        this.scroll_Y = scroll_Y;
    }

    public void setHeight_view(int height_view) {
        this.height_view = height_view;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void setmPosY(int mPosY) {
        this.mPosY = mPosY;
    }

    public void setLl_contain(LinearLayout ll_contain) {
        this.ll_contain = ll_contain;
    }
}
