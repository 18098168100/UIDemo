package com.linktrust.student.heartreelibrary;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.heartreelibrary
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/6/13 11:27
 */
public class Snaphot {

    Canvas canvas;
    Bitmap bitmap;

    public Snaphot( Bitmap bitmap) {
        this.bitmap = bitmap;
        this.canvas = new Canvas(bitmap);
    }
}
