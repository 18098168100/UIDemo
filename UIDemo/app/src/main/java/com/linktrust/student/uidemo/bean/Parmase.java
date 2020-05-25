package com.linktrust.student.uidemo.bean;

import com.linktrust.student.uidemo.SecondActivity;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.bean
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/6/5 15:21
 */
public class Parmase {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "[" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ']';
    }
}

