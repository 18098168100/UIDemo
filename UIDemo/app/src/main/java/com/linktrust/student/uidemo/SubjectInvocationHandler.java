package com.linktrust.student.uidemo;

import android.util.Log;

import com.linktrust.student.uidemo.bean.RealSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/6/11 16:26
 */
public class SubjectInvocationHandler implements InvocationHandler{
    private RealSubject subject;

    public SubjectInvocationHandler(RealSubject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作

        Log.e("haohai","Method:" + method);
        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);
        //在代理真实对象后我们也可以添加一些自己的操作
       Log.e("haohai","after Method invoke");
        return null;
    }
}
