package com.linktrust.student.uidemo;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.uidemo
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/8/22 10:14
 */
public interface Service {
    @GET
    Call<Hot> listRepos();
}
