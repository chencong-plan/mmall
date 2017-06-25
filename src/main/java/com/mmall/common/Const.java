package com.mmall.common;

/**
 * 项目：  mmall
 * 包名：  com.mmall.common
 * 作者：  chencong
 * 时间：  2017/5/25 23:41.
 * 描述：  常量类
 */
public class Const {

    //session 的键
    public static final String CURRENT_USER = "currentUser";

    //type类别信息
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    //角色接口类
    public interface Role {
        int ROLE_USTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;   //管理员
    }
}
