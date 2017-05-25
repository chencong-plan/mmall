package com.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * 项目：  mmall
 * 包名：  com.mmall.common
 * 作者：  chencong
 * 时间：  2017/5/25 18:15.
 * 描述：  Service通用型接口
 * 私有构造方法 不能被外部初始
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证json序列化时候，如果是null对象，那么key也会消失
public class ServerResponse<T> implements Serializable {

    //状态
    private int status;
    //返回信息
    private String msg;
    //返回泛型
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 判断响应是否成功 和ResponseCode里面定义的响应编码进行对照
     * 使用注解，使得在json序列化返回是不出现显示在json中
     *
     * @return 响应成功返回true  否则返回false
     */
    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    /**
     * 返回响应状态code，在json序列化时会显示在json中
     *
     * @return 返回响应状态code
     */
    public int getStatus() {
        return status;
    }

    /**
     * 返回泛型数据data 在json序列化时会显示在json中
     *
     * @return 返回泛型数据data
     */
    public T getData() {
        return data;
    }

    /**
     * 返回响应描述信息 在json序列化时会显示在json中
     *
     * @return 返回响应描述信息
     */
    public String getMsg() {
        return msg;
    }


    /**
     * 创建一个成功的服务器响应
     * 返回status
     * 将ResponseCode的getCode作为ServerResponse的status返回
     * code是ResponseCode里面的枚举状态代码
     *
     * @param <T>
     * @return 返回一个ServerResponse构造器（ResponseCode中成功code）
     */
    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 创建一个成功的服务器响应
     * 将ResponseCode的getCode作为ServerResponse的status，msg作为状态描述信息
     * 创建成功返回一个msg文本供前端使用
     *
     * @param msg 描述信息
     * @param <T>
     * @return 返回一个ServerResponse构造器（ResponseCode中成功code，msg描述信息）
     */
    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    /**
     * 创建一个成功的服务器响应，并且将服务器状态code，data数据传递进去返回
     *
     * @param data data数据
     * @param <T>
     * @return 返回一个ServerResponse构造器（ResponseCode中成功code，数据data）
     */
    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    /**
     * 创建有一个成功的服务器响应 并且将消息msg，data数据传递进去返回
     * 此静态方法解决"将String作为 T 还是Sting字符串"调用构造器的矛盾问题
     *
     * @param msg  消息msg数据
     * @param data data数据
     * @param <T>
     * @return 返回一个ServerResponse构造器（ResponseCode中成功code，msg描述信息，数据data）
     */
    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }


    /**
     * 创建一个失败的服务器响应 并且将ResponseCode的code和desc作为参数传递返回
     * @param <T>
     * @return 返回一个ServerResponse构造器（ResponseCode中错误code，desc描述信息）
     */
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    /**
     * 创建一个错误的服务器响应
     * @param errorMessage 错误代码
     * @param <T>
     * @return 返回一个ServerResponse构造器
     */
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return  new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }


    /**
     * 创建一个参数错误的服务器响应
     * @param errorCode 错误代码
     * @param errorMessage 错误描述
     * @param <T>
     * @return 返回一个ServerResponse构造器（错误代码status,错误描述）
     */
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return  new ServerResponse<T>(errorCode,errorMessage);
    }
}













