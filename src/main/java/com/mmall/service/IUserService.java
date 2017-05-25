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

    ServerResponse<User> login(String username, String password);
}
