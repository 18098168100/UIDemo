package com.linktrust.student.uidemo.bean;

import android.util.Log;

import com.linktrust.student.uidemo.i.Subject;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.bean
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/6/11 16:25
 */
public class RealSubject implements Subject{
    @Override
    public void sayGoodBye() {
        Log.e("haohai","RealSubject sayGoodBye");

    }

    @Override
    public void sayHello(String str) {
        Log.e("haohai","RealSubject sayHello  " + str);

    }
}
