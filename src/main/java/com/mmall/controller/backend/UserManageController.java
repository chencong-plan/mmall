package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 项目：  mmall
 * 包名：  com.mmall.controller.backend
 * 作者：  chencong
 * 时间：  2017/6/24 23:09.
 * 描述：  后台管理员界面
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    /**
     * 后台管理员登录
     *
     * @param username 用户名
     * @param password 密码
     * @param session  登录信息放入到session中
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {

        //直接复用用户登录接口
        ServerResponse<User> response = iUserService.login(username, password);
        //判断请求是否成功
        if (response.isSuccess()) {
            User user = response.getData();
            //判断当前用户角色是否为管理员 如果是 则将用户信息放入session中
            if (user.getRole() == Const.Role.ROLE_ADMIN) {
                session.setAttribute(Const.CURRENT_USER, user);
                return response;
            } else {
                return ServerResponse.createByErrorMessage("不是管理员，无法登录");
            }
        }
        return response;
    }
}
