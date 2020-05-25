package com.linktrust.student.interfacemodle;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.interfacemodle
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/7/15 9:38
 */
public abstract class FunctionHasParamNoResult<P> extends Function {

    public FunctionHasParamNoResult(String functionName) {
        super(functionName);
    }

    public abstract void function(P p);
}
