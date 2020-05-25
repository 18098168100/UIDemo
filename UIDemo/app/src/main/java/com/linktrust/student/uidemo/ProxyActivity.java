package com.linktrust.student.uidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.linktrust.student.uidemo.bean.RealSubject;
import com.linktrust.student.uidemo.i.Subject;

import java.lang.reflect.Proxy;

public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        //被代理类
        Subject realSubject = new RealSubject();
        //我们要代理哪个类，就将该对象传进去，最后是通过该被代理对象来调用其方法的
        SubjectInvocationHandler handler = new SubjectInvocationHandler((RealSubject) realSubject);
        //生成代理类对象
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),handler);

        subject.sayHello("2222");

    }
}
