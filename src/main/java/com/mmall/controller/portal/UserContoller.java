package com.mmall.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 项目：  mmall
 * 包名：  com.mmall.controller.portal
 * 作者：  chencong
 * 时间：  2017/5/25 18:00.
 * 描述：  控制层 用户
 */
//控制层注解
@Controller
//将这一层的url都注解成/user/下
@RequestMapping("/user/")
public class UserContoller {


    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    //请求方式只能为post
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    //使用spring注解 将返回的内容自动序列化为json
    @ResponseBody
    public  Object login(String username, String password, HttpSession session){
        //开始调用service --> mybatis -->dao
        return null;
    }
}
