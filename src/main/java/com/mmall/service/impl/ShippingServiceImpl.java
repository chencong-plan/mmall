package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ShippingMapper;
import com.mmall.pojo.Shipping;
import com.mmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 项目：  mmall
 * 包名：  com.mmall.service.impl
 * 作者：  chencong
 * 时间：  2017/7/2 12:43.
 * 描述：  收货地址服务接口实现类
 */
@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {


    @Autowired
    private ShippingMapper shippingMapper;


    /**
     * 新增收件地址
     * <p>新增成功，则将收件地址ID返回</p>
     *
     * @param userId   用户ID
     * @param shipping 收件地址实体
     * @return 返回服务器响应
     */
    public ServerResponse add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("shippingId", shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功", result);
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }

    /**
     * 删除收件地址
     * <p>首先需要判断该用户下是否存在这一个收件地址，或者被操作的收件地址是否属于该用户</p>
     * 然后在进行删除操作，防止横向越权
     *
     * @param userId     用户ID
     * @param shippingId 本操作收件地址ID
     * @return 返回服务器响应
     */
    public ServerResponse<String> delete(Integer userId, Integer shippingId) {
        int resultCount = shippingMapper.deleteByShippingIdAnduserId(userId, shippingId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }

    /**
     * 修改收件地址
     * <p>根据用户ID和收件地址修改该用户的该条收件地址</p>
     *
     * @param userId   用户ID
     * @param shipping 收件实体
     * @return 返回服务器响应
     */
    public ServerResponse update(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.updateByShipping(shipping);
        if (rowCount > 0) {

            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    /**
     * 根据用户id和收件地址ID查询该收件地址详细信息
     *
     * @param userId     用户ID
     * @param shippingId 收件地址ID
     * @return 返回服务器响应
     */
    public ServerResponse<Shipping> select(Integer userId, Integer shippingId) {
        Shipping shipping = shippingMapper.selectByShippingAnduserId(userId, shippingId);
        if (shipping == null) {
            return ServerResponse.createByErrorMessage("无法查询到改地址");
        }
        return ServerResponse.createBySuccess("查询地址成功", shipping);
    }

    /**
     * 根据用户ID返回当前用户的所有收件地址
     * <p>分页形式返回</p>
     *
     * @param userId   用户ID
     * @param pageNum  当前页码
     * @param pageSize 每页数量
     * @return 返回服务器响应
     */
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //根据用户ID查询所有地址
        List<Shipping> shippingList = shippingMapper.selectShippingListByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }


}
