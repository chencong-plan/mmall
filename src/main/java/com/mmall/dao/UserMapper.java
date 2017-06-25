package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //校验用户名是否存在
    int checkUsername(String username);

    /**
     * 检查用户邮箱是否存在
     *
     * @param email 邮箱
     * @return 返回查找数量
     */
    int checkEmail(String email);

    //mybatis 多个参数时候需要用到@param注解 这里的注解和sql语句里面保持一致
    //这里是查找数据库用户 返回User对象
    User selectLogin(@Param("username") String username, @Param("password") String password);

    //通过用户名查找密码提示问题
    String selectQuestionByUsername(String username);

    /**
     * 查找问题答案数量
     * 如果返回大于零（1） 那么说明问题以及答案正确，否则答案错误
     *
     * @param username 用户名
     * @param question 问题
     * @param answer   答案
     * @return 返回数量
     */
    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    /**
     * 用户忘记密码时重置密码
     *
     * @param username    用户名
     * @param passwordNew 新密码
     * @return 修改的行数
     */
    int updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);

    /**
     * 检查用户密码
     *
     * @param password 密码
     * @param userId   用户id
     * @return
     */
    int checkPassword(@Param("password") String password, @Param("userId") Integer userId);

    /**
     * 通过用户id检验email是否存在
     *
     * @param email  邮箱
     * @param userId 用户ID
     * @return 存在数量
     */
    int checkEmailByUserId(@Param("email") String email, @Param("userId") Integer userId);
}