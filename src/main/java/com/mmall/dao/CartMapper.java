package com.mmall.dao;

import com.mmall.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    /**
     * 根据用户ID和商品ID查询购物车
     *
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 返回查询到的cart实体
     */
    Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    /**
     * 通过用户ID(userId)查找cart
     *
     * @param userId 用户ID
     * @return list
     */
    List<Cart> selectCartByUserId(Integer userId);

    /**
     * 根据用户ID查看购物车中是否选中状态,返回购物车中checked==0的个数
     * <p>全勾选则返回0</p>
     * 有未勾选则返回不是0
     *
     * @param userId 用户ID
     * @return 返回int
     */
    int selectCartProductCheckedStatusByUserId(Integer userId);

    /**
     * 根据商品ID集合删除购物车中所有商品
     *
     * @param userId        用户ID
     * @param productIdList 商品ID编号
     * @return 无返回
     */
    int deleteByUserIdProductId(@Param("userId") Integer userId, @Param("productIdList") List<String> productIdList);


    /**
     * 全选商品
     * <p>反选商品</p>
     * 选中某一个商品
     *
     * @param userId    用户ID
     * @param checked   是否选中
     * @param productId 商品ID
     * @return 无返回
     */
    int checkedOrUncheckedProduct(@Param("userId") Integer userId, @Param("checked") Integer checked, @Param("productId") Integer productId);

    /**
     * 查询用户购物车中商品数量
     *
     * @param userId 用户ID
     * @return 返回服务器响应
     */
    int selectCartProductCount(Integer userId);
}