package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * 项目：  mmall
 * 包名：  com.mmall.service
 * 作者：  chencong
 * 时间：  2017/7/1 16:28.
 * 描述：  用户购物车商品服务
 */
public interface ICartService {

    /**
     * 查询用户购物车
     *
     * @param userId 用户ID
     * @return 返回服务器响应
     */
    ServerResponse<CartVo> list(Integer userId);

    /**
     * 添加商品进入购物车
     *
     * @param userId    用户ID
     * @param productId 商品ID
     * @param count     将要添加进入购物车的商品数量
     * @return 返回服务器响应
     */
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    /**
     * 更新购物车中商品数量
     *
     * @param userId    用户ID
     * @param productId 商品ID
     * @param count     将要更新的商品数量
     * @return 返回服务器响应
     */
    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    /**
     * 购物车中删除商品
     *
     * @param userId     用户id
     * @param productIds 要从购物车中移除的商品ID集合
     * @return 返回服务器响应
     */
    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    /**
     * 根据checked判断全选或者反选
     *
     * @param userId    用户ID
     * @param checked   1==全选，0==反选
     * @param productId 商品ID
     * @return 返回服务器响应
     */
    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer checked, Integer productId);

    /**
     * 查找用户购物车中商品数量
     *
     * @param userId 用户ID
     * @return 返回服务器响应
     */
    ServerResponse<Integer> getCartProductCount(Integer userId);
}
