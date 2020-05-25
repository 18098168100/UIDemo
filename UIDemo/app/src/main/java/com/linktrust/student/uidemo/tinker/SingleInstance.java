package com.linktrust.student.uidemo.tinker;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.tinker
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/8/15 9:26
 */
public class SingleInstance {
    
    private static volatile SingleInstance instance;
    
    private SingleInstance(){};
    
    public static SingleInstance getInstance(){
        //只有对象为空的时候才去等待同步锁
        if (instance == null){
            synchronized (SingleInstance.class){
                if (instance == null){
                    instance = new SingleInstance();
                }
            }
        }
        return instance;
    }

}

