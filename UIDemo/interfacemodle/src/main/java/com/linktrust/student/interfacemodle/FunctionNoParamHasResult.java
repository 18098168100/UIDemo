package com.linktrust.student.interfacemodle;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.interfacemodle
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/7/15 9:36
 */
public abstract class FunctionNoParamHasResult<T> extends Function{

    public FunctionNoParamHasResult(String functionName) {
        super(functionName);
    }

    public abstract T function();
}
