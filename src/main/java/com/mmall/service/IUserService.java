package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * 项目：  mmall
 * 包名：  com.mmall.service
 * 作者：  chencong
 * 时间：  2017/5/25 18:11.
 * 描述：  service 用户接口
 */
public interface IUserService {

    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    ServerResponse<User> login(String username, String password);

    /**
     * 注册接口
     *
     * @param user 用户对象
     * @return
     */
    ServerResponse<String> register(User user);

    /**
     * 校验用户名密码是否已经存在于数据库当中
     *
     * @param str  字符串
     * @param type 类别
     * @return
     */
    ServerResponse<String> checkValid(String str, String type);

    /**
     * 根据用户名查找密码提示问题
     *
     * @param username 用户名
     * @return
     */
    ServerResponse selectQuestion(String username);

    /**
     * 查找username的问题和答案在数据库中是否匹配
     * 如果匹配返回count大于0
     *
     * @param username 用户名
     * @param question 问题
     * @param answer   答案
     * @return
     */
    ServerResponse<String> checkAnswer(String username, String question, String answer);

    /**
     * 忘记密码时候的重置密码
     *
     * @param username    用户名
     * @param passwordNew 新密码
     * @param forgetToken 获取到的token
     * @return
     */
    ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken);

    /**
     * 登录状态下修改密码
     *
     * @param passwordOld 旧密码
     * @param passwordNew 新密码
     * @param user        当前用户user
     * @return
     */
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    /**
     * 登录状态下跟新个人信息
     *
     * @param user 当前用户
     * @return
     */
    ServerResponse<User> updateInformation(User user);

    /**
     * 根据用户id获取到用户详细信息
     *
     * @param userId 用户id
     * @return
     */
    ServerResponse<User> getInformation(Integer userId);

    /**
     * 检查是否是管理员
     *
     * @param user 管理员user
     * @return
     */
    ServerResponse checkAdminRole(User user);
}
