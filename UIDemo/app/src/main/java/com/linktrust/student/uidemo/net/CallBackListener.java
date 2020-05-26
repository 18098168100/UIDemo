package com.linktrust.student.uidemo.net;

import java.io.InputStream;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo.net
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2020/5/26 10:58
 */
interface CallBackListener {

    void onsuccess(InputStream inputStream);

    void onFailure();
}
