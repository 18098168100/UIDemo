package com.linktrust.student.heartreelibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.heartreelibrary
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/6/13 10:29
 */
public class TreeView extends View{

    //new 对象
    public TreeView(Context context) {
        super(context);
    }


    //配置XML属性
    public TreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0xff000000);
    }
}
