package com.linktrust.student.uidemo.net;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.net
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/26 15:29
 */
public class ResponseClass {

    private String resultcode;
    private String reason;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ResponseClass{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
