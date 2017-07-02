package com.mmall.dao;

import com.mmall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    /**
     * 防止横向越权
     * <p>根据shippingId和用户ID进行删除收获地址</p>
     *
     * @param userId     用户ID
     * @param shippingId 收件地址
     * @return 返回int
     */
    int deleteByShippingIdAnduserId(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);


    int updateByShipping(Shipping record);

    /**
     * 根据用户ID和收件地址ID查询某条收件地址详情
     *
     * @param userId     用户ID
     * @param shippingId 收件地址ID
     * @return 返回Shipping实体
     */
    Shipping selectByShippingAnduserId(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);

    /**
     * 根据用户ID返回该用户的所有收件信息
     *
     * @param userId 用户ID
     * @return 返回list
     */
    List<Shipping> selectShippingListByUserId(@Param("userId") Integer userId);
}