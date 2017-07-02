package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;

/**
 * 项目：  mmall
 * 包名：  com.mmall.service
 * 作者：  chencong
 * 时间：  2017/7/2 12:42.
 * 描述：  收货地址service接口
 */
public interface IShippingService {

    /**
     * 新增收件地址，
     * 返回新增收件地址ID
     *
     * @param userId   用户ID
     * @param shipping 收件地址实体
     * @return 返回服务器响应
     */
    ServerResponse add(Integer userId, Shipping shipping);

    /**
     * 根据用户ID，收件地址ID删除该用户的收件信息
     *
     * @param userId     用户ID
     * @param shippingId 收件地址ID
     * @return 返回服务器响应
     */
    ServerResponse<String> delete(Integer userId, Integer shippingId);

    /**
     * 根据用户ID，收件地址实体修改该收件地址
     *
     * @param userId   用户ID
     * @param shipping 被修改的收件实体
     * @return 返回服务器响应
     */
    ServerResponse update(Integer userId, Shipping shipping);

    /**
     * 根据用户ID，收件地址ID查询到该收件地址详细信息
     *
     * @param userId     用户ID
     * @param shippingId 收件地址ID
     * @return 返回服务器响应
     */
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    /**
     * 根据用户ID以分页形式返回当前用户的所有收件地址
     *
     * @param userId   用户ID
     * @param pageNum  当前页码
     * @param pageSize 每页数量
     * @return 返回服务器响应
     */
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
