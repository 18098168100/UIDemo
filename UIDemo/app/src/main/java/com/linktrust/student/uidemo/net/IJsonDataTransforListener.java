package com.linktrust.student.uidemo.net;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.net
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/26 15:08
 */
public interface IJsonDataTransforListener<T> {

    void onSuccess(T t);
}
