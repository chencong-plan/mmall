package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
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
 * 包名：  com.mmall.controller.portal
 * 作者：  chencong
 * 时间：  2017/5/25 18:00.
 * 描述：  控制层 用户
 */
//控制层注解
@Controller
//将这一层的url都注解成/user/下
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;


    /**
     * 用户登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @param session  登录成功返回session
     * @return 返回一个response
     */
    //请求方式只能为post
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    //使用spring注解 将返回的内容自动序列化为json
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        //开始调用service --> mybatis -->dao
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            //如果response成功 设置session
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 用户登出接口
     *
     * @param session 被清除的session
     * @return 返回createBySuccess创建成功信息
     */
    @RequestMapping(value = "login_out.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> loginOut(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccessMessage("退出成功");
    }


    /**
     * 用户注册接口
     *
     * @param user 用户信息对象
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }


    /**
     * 实时判断用户名和email信息的接口
     *
     * @param str  输入的字符串
     * @param type 字符串的类别，用户名或者email
     * @return
     */
    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return iUserService.checkValid(str, type);
    }

    /**
     * 获得当前用户信息接口
     * 如果存在返回用户信息user，如果不存在就返回ServerResponse信息
     *
     * @param session 浏览器session是否存在用户信息
     * @return
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获得用户当前信息");
    }

    /**
     * 忘记密码时，获取密码提示问题
     * 返回username的密码提示问题
     *
     * @param username 用户名
     * @return 密码提示问题
     */
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return iUserService.selectQuestion(username);
    }


    /**
     * 校验问题答案是否正确接口
     *
     * @param username 用户名
     * @param question 问题
     * @param answer   问题答案
     * @return
     */
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return iUserService.checkAnswer(username, question, answer);
    }


    /**
     * 忘记密码时候重置密码
     *
     * @param username    用户名
     * @param passwordNew 新密码
     * @param forgetToken 获取到的token
     * @return
     */
    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return iUserService.forgetRestPassword(username, passwordNew, forgetToken);
    }

    /**
     * 登录状态下修改密码
     *
     * @param session     当前session
     * @param passowrdOld 旧密码
     * @param passwordNew 新密码
     * @return
     */
    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passowrdOld, String passwordNew) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passowrdOld, passwordNew, user);
    }


    /**
     * 登录状态下更新用户信息
     *
     * @param session session
     * @param user    当前用户
     * @return
     */
    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInfomation(HttpSession session, User user) {
        //首先判断用户是否登录 只有在登录状态下才能够修改信息
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        //从登录状态中获取用户username 和 id
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = iUserService.updateInfomation(user);
        if (response.isSuccess()) {
            //更新session
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 从session中获取用户信息
     * 如果用户没有登录 强制登录
     *
     * @param session 获取session
     * @return 用户信息
     */
    @RequestMapping(value = "get_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInfomation(HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录，需强制登录");
        }
        return iUserService.getInfomation(currentUser.getId());
    }

}










