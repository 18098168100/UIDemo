package com.linktrust.student.heartreelibrary;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.LinkedList;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.heartreelibrary
 * 类描述:树干
 * 创建人:hh
 * 创建时间:2019/6/13 11:24
 */
public class Branch {
    private static int branchColor = 0xFF775533;
    private Point [] cp = new Point[3];
    private int currentLength;
    private float maxLength;
    private float radius;
    private float part;

    LinkedList<Branch> linkedList;
    private float growX;
    private float growY;

    Branch(int [] data){
        cp[0] = new Point(data[2],data[3]);
        cp[1] = new Point(data[4],data[5]);
        cp[2] = new Point(data[6],data[7]);
        radius = data[8];
        maxLength = data[9];
        part = 1.0f/maxLength;
    }

    public boolean grow(Canvas canvas,float scaleFactor){
        if (currentLength < maxLength){
            bazier(part * currentLength);
            draw(canvas,scaleFactor);
            currentLength ++;
            radius*= 0.97f;
            return true;
        }else {
            return false;
        }

    }

    /**
     * 开始绘制
     * @param canvas
     * @param scaleFactor 缩放因子
     */
    private void draw(Canvas canvas, float scaleFactor) {
        Paint paint = CommonUtil.getPaint();
        paint.setColor(branchColor);


        canvas.save();
        canvas.scale(scaleFactor,scaleFactor);
        canvas.translate(growX,growY);
        canvas.drawCircle(0,0,radius,paint);
        canvas.restore();

    }


    /**
     * 获取二级贝塞尔曲线的滑动点的位置
     * 通用公式 B（t）= (1-t)(1-t)P0 + 2t(1-t)P1+t*tP2,t[0,1]
     */
    private void bazier(float t) {
        growX = (1-t)*(1-t)*cp[0].x + 2*t*(1-t)*cp[1].x+t*t*cp[2].x;
        growY = (1-t)*(1-t)*cp[0].y + 2*t*(1-t)*cp[1].y+t*t*cp[2].y;
    }
}
