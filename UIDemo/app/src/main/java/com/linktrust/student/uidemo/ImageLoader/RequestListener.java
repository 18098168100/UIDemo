package com.linktrust.student.uidemo.ImageLoader;

import android.graphics.Bitmap;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.ImageLoader
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/22 15:30
 */
public interface RequestListener {

    boolean onSuccess(Bitmap bitmap);

    boolean onFailure();
}
