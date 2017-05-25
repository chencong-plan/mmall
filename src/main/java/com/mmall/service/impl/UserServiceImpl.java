package com.mmall.service.impl;

import com.mmall.common.ServerResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 项目：  mmall
 * 包名：  com.mmall.service.impl
 * 作者：  chencong
 * 时间：  2017/5/25 18:13.
 * 描述：  TODO
 */
public class UserServiceImpl implements IUserService {

    //使用AutoWrited注解将UserMapper注解进来
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<User> login(String username, String password) {
        return null;
    }
}
