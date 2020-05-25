package com.linktrust.student.uidemo.ImageLoader;

import android.content.Context;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.ImageLoader
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/25 13:45
 */
public class NeGlide {
    public static BitmapRequest with(Context context){
        return new BitmapRequest(context);
    }
}
