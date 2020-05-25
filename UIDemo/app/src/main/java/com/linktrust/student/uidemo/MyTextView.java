package com.linktrust.student.uidemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/5/31 14:10
 */
public class MyTextView extends AppCompatTextView {
    private int mX;
    private int mY;
    private int moveX;
    private int moveY;
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mX = (int) event.getX();
                mY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = (int) (event.getX() - mX);
                moveY = (int) (event.getY() - mY);
                layout(getLeft()+moveX,getTop()+moveY,getRight()+moveX,getBottom()+moveY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}
