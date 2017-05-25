package com.mmall.common;

/**
 * 项目：  mmall
 * 包名：  com.mmall.common
 * 作者：  chencong
 * 时间：  2017/5/25 18:29.
 * 描述：  响应编码枚举类 状态代码
 */
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(0,"SUCCESS"),
    /**
     * 错误
     */
    ERROR(1,"ERROR"),
    /**
     * 需要登录
     */
    NEED_LOGIN(10,"NEED_LOGIN"),
    /**
     * 参数错误
     */
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }

}
