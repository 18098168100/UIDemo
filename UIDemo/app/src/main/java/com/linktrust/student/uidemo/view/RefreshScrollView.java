package com.linktrust.student.uidemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.view
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/6/10 13:34
 */
public class RefreshScrollView extends ScrollView {

    //记录当前scrollview 滑动的距离
    private int current_scroll_y;
    //透明控件的高度
    private int height_view;
    /**
     * 重写构造函数，这里不是重点
     */
    public RefreshScrollView(Context context) {
        this(context,null);
    }

    public RefreshScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getWidth(); //获取ScrollView的宽度用来设置头布局的宽度
    }

    private int down_y; //按下时候的y坐标
    private int scroll_y;  //ScrollView的滑动距离
    private View headViewRefresh;  //头布局
    private RefreshListener listsner;  //刷新加载数据监听
    private boolean b_down; //是否可以刷新
    private int viewWidth;  //scrollView宽度
    private int headViewHeight;  //头布局刷新时的高度

    /**
     * 设置刷新头布局
     * @param view
     */
    public void setHeadView(View view){  //在引用此自定义ScrollView的activity中传入初始化完成的头布局文件
        this.headViewRefresh = view;
        this.headViewHeight = dip2px(getContext(),50); //dp转px,px转dp工具，下文给出
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) headViewRefresh.getLayoutParams();
        params.width = viewWidth;
        params.height = 0;
        headViewRefresh.setLayoutParams(params); //将headView的高度重新设置为0,也就是不可见，为什么这么设置？下文会介绍
    }

    /**
     * 提供给调用scrollView的页面的刷新加载回调方法
     * @param listsner
     */
    public void setListsner(RefreshListener listsner){ //回调接口下文给出
        this.listsner = listsner;
    }

    /**
     * 刷新停止,给scrollView外部调用
     */
    public void stopRefresh() {
        //listsner.hintChange("下拉刷新");  //停止刷新之后，将提示文字设置成初始值，时刻准备着下次刷新
//        headViewRefresh.setVisibility(View.GONE);  //隐藏headView
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) headViewRefresh.getLayoutParams();  //将headView的高度重新设置为1
        params.width = viewWidth;
        params.height = 0;
        headViewRefresh.setLayoutParams(params); //设置头布局的高度为0,也就是隐藏头布局
    }

    @Override
    protected void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
        scroll_y = scrollY;  //监听赋值，监听scrollView的滑动状态，当滑动到顶部的时候才可以下拉刷新
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) { //重写dispatchTouchEvent,敲黑板，这是重点！
        if(event.getAction() == MotionEvent.ACTION_DOWN){   //获取手指初次触摸位置
            down_y = (int) event.getY();  //记录下手指点下的纵坐标
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){   //滑动事件
            if(scroll_y == 0){   //如果scroll_y == 0,在顶部，可以刷新
                if (current_scroll_y > 0){
                    down_y = (int) event.getY();
                    current_scroll_y = 0;
                }
                if(event.getY() - down_y > 0){  //手势判断：向下滑动,可以刷新
                    //event.getY()-down_y是手指滑动的纵向距离，为什么乘1/3?为了让下拉刷新更肉一点，这样手指下滑300像素，头布局高度增高100像素，可根据个人喜好做出调整

                    int downRange = (int) ((event.getY()-down_y)*1/3);   //给headView动态设置高度，动态高度是手指向下滑动距离的1/3
                    headViewRefresh.setVisibility(View.VISIBLE);  //显示刷新的根视图  显示headView控件
                    b_down = false;    //刚开始滑动，松手还不可以刷新
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) headViewRefresh.getLayoutParams();  //将滑动距离转化后的值用来给headView动态设置高度
                    params.width = viewWidth;
                    params.height = downRange;
                    headViewRefresh.setLayoutParams(params);
                    if(downRange >= headViewHeight){
                        b_down = true; //可以刷新
                    }else{     //当动态设置的高度不大于初始高度的时候，变换hint，此时松手不可刷新
                        b_down = false; //不可以刷新
                    }
                    return true;   //拦截触摸事件，scrollView不可响应触摸事件，否则会造成松手滑动跳动错位
                }else{ //手势判断：小于0则是上滑，此时按正常程序走
                    b_down = false; //不可以刷新
                    return super.dispatchTouchEvent(event);  //向上滑动，不拦截
                }
            }else{ //scroll_y不等于0则是上滑，此时按正常程序走
                b_down = false; //不可以刷新
                return super.dispatchTouchEvent(event);   //scrollView不在顶部，不拦截
            }
        }
        if(event.getAction() == MotionEvent.ACTION_UP){   //抬起手指
            if(b_down){    //如果可以刷新
                listsner.startRefresh();
            }else{    //如果不可以刷新，停止刷新
                stopRefresh();
            }
            current_scroll_y = scroll_y;
        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getY() < (height_view - scroll_y) || ev.getY() < (height_view - scroll_y)) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    /** dip转换px */
    public static int dip2px(Context context, int dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** pxz转换dip */
    public static int px2dip(Context context,int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    public interface RefreshListener {
        void startRefresh(); //刷新
//        void loadMore();  //加载
//        void hintChange(String hint);  //提示文字
//        void setWidthX(int x);  //设置x
    }

    public void setHeight_view(int height_view) {
        this.height_view = height_view;
    }
}
