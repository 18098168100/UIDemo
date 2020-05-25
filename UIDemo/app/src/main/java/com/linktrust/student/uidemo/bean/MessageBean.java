package com.linktrust.student.uidemo.bean;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.bean
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/6/18 10:10
 */
public class MessageBean {
    private String msg;
    private String num;

    public MessageBean(String msg, String num) {
        this.msg = msg;
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public MessageBean(String msg) {
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(num);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
         if (obj instanceof MessageBean){
             if (((MessageBean) obj).msg.equals(this.msg)){
                 return true;
             }
         }
        return false;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "msg='" + msg + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
