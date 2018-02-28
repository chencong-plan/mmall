package com.mmall.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.common.TokenCache;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 项目：  mmall
 * 包名：  com.mmall.service.impl
 * 作者：  chencong
 * 时间：  2017/5/25 18:13.
 * 描述：  服务层 用户
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    /**
     * 使用AutoWrited注解将UserMapper注解进来
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * 首先查找数据库用户是否存在(checkUsername)是否为0; 0 --->为空不存在该用户  1--->存在用户进行下列(selectLogin)操作
     * 查找数据库用户名密码是否正确(selectLogin) 返回是否为null null--->密码错误
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<User> login(String username, String password) {
        //使用checkUsername查找数据库 查看是否存在username用户
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        //todo 密码登录MD5
        //第一次注册数据库中存储的是password的MD5加密后的结果，因此在这里就是将md5进行比较
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        //查找用户username 的密码是否正确
        User user = userMapper.selectLogin(username, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        //否则用户存在 密码正确登录成功将其密码置空
        user.setPassword(StringUtils.EMPTY);
        //返回成功信息
        return ServerResponse.createBySuccess("登录成功", user);
    }

    /**
     * 首先校验用户名 email 是否存在,若存在返回ServerResponse信息
     *
     * @param user user对象
     * @return ServerResponse信息
     */
    @Override
    public ServerResponse<String> register(User user) {
        //复用下面的checkValid校验方法
        ServerResponse validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }

        //如果用户名不存在，邮箱也不存在，该注册用户是新用户，那么就将其角色设置为普通用户
        user.setRole(Const.Role.ROLE_USTOMER);
        //MD5加密 将用户密码进行加密 在数据库中不能明文存储密码
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        //将加密结果存储到数据库
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }


    /**
     * 校验输入的结果是否已经在数据库中存在了
     *
     * @param str  输入的字符串
     * @param type 字符串的类别
     * @return
     */
    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNoneBlank(type)) {
            //type不为空 开始校验用户名或者密码
            if (Const.USERNAME.equals(type)) {
                //校验用户名
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已经存在");
                }
            }

            if (Const.EMAIL.equals(type)) {
                //开始校验密码
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("email已经存在");
                }
            }

        } else {
            //返回参数错误
            return ServerResponse.createByErrorMessage("参数错误");
        }
        //当结果都通过时候 就返回信息了
        return ServerResponse.createBySuccessMessage("校验成功");
    }


    /**
     * 查找username的密码提示问题
     * 首先校验输入的username是否存在（复用checkvalid方法）
     *
     * @param username
     * @return
     */
    @Override
    public ServerResponse selectQuestion(String username) {
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            //用户不存在
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        //如果username存在，那么开始查找username的question
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNoneBlank(question)) {
            //如果查找到的密码提示问题不为空 就将其返回
            return ServerResponse.createBySuccess(question);
        }
        //否则返回错误提示信息
        return ServerResponse.createByErrorMessage("找回密码的为题是空的");
    }


    /**
     * 将用户名 问题 答案进行数据库查找匹配，返回数量resultCount
     * 如果resultCount > 0 说明问题以及答案是这个用户的，并且正确
     * 生成forgetToken 并且将其放入本地cache中，并且设置有效期
     * 问题和相应答案在数据库中没有找到，返回ServerResponse的ErrorMessage信息
     *
     * @param username 用户名
     * @param question 问题
     * @param answer   问题答案
     * @return
     */
    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if (resultCount > 0) {
            //说明问题以及问题答案是这个用户的，并且是正确的
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题的答案错误");
    }


    /**
     * 忘记密码时候的修改密码
     *
     * @param username    用户名
     * @param passwordNew 新密码
     * @param forgetToken 产生的token
     * @return
     */
    @Override
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误，token需要传递");
        }
        //判断用户是否存在
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            //用户名不存在
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        //从cache中获取token
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        //对cache中的token进行验证
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("Token无效或者过期");
        }
        if (StringUtils.equals(forgetToken, token)) {
            //跟新密码 跟新的是md5密码
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByUsername(username, md5Password);
            if (rowCount > 0) {
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("Token错误，请重新获取重置密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }


    /**
     * 登录状态下修改密码
     *
     * @param passwordOld 旧密码
     * @param passwordNew 新密码
     * @param user        用户user
     * @return
     */
    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        //防止横向越权，要检验一下这个用户的旧密码，一定要指向这个用户 会查询一个count出来 如果不指定id  count大于零

        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        //跟新用户信息
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码跟新失败");
    }

    /**
     * 登录状态下修改个人信息
     * username是不能够被修改的
     * email也需要进行校验，检验新的email是否存在
     * 并且存在的email相同，不能是当前这个用户的
     *
     * @param user 用户
     * @return
     */
    @Override
    public ServerResponse<User> updateInformation(User user) {

        //首先进行验证用户email
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if (resultCount > 0) {
            //邮箱已经存在 并且不属于当前用户的
            return ServerResponse.createByErrorMessage("email已经存在，更换email再尝试更新");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());

        //跟新用户
        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0) {
            return ServerResponse.createBySuccess("跟新个人信息成功", updateUser);
        }
        //失败
        return ServerResponse.createByErrorMessage("跟新个人信息失败");
    }

    /**
     * 根据用户id获取用户user详细信息
     *
     * @param userId 用户id
     * @return 密码置空的user
     */
    @Override
    public ServerResponse<User> getInformation(Integer userId) {
        //通过主键查询用户
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createByErrorMessage("找不到当前用户信息");
        }
        //找到userId的用户信息后 将用户密码置空 返回
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }

    // backend 后台部分

    /**
     * 校验是否是管理员
     *
     * @param user 用户信息
     * @return 返回服务器响应数据
     */
    @Override
    public ServerResponse checkAdminRole(User user) {
        if (user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

}







