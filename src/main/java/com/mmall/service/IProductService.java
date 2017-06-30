package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

/**
 * 项目：  mmall
 * 包名：  com.mmall.service
 * 作者：  chencong
 * 时间：  2017/6/28 15:10.
 * 描述：  商品接口
 */
public interface IProductService {

    /**
     * 新增 或 更新商品信息
     *
     * @param product 将要操作的商品pojo
     * @return 返回服务器响应
     */
    ServerResponse saveOrUpdateProduct(Product product);

    /**
     * 修改产品销售状态
     *
     * @param productId 将要修改产品的ID
     * @param status    被修改产品的目标状态
     * @return 返回服务器响应
     */
    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    /**
     * 展示商品详细信息
     *
     * @param productId 将要展示的商品ID
     * @return 返回商品VO
     */
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    /**
     * pageHelper分页
     *
     * @param pageNum  当前页数
     * @param pageSize 每页显示数量
     * @return 返回pageinfo
     */
    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    /**
     * 后台根据商品名称、商品ID进行查询商品，结果以分页显示
     *
     * @param productName 被查询商品名称
     * @param productId   被查询商品ID
     * @param pageNum     当前显示的页码
     * @param pageSize    每页显示的数量
     * @return 返回服务器响应
     */
    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    /**
     * 前台
     * 根据商品ID获取商品详情
     *
     * @param productId 商品ID
     * @return 返回服务器响应
     */
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    /**
     * 前台
     * 根据关键字或者分类ID搜索商品信息，并且按照指定排序规则进行排序
     * 结果以分页形式返回
     *
     * @param keyword    关键字
     * @param categoryId 分类ID
     * @param pageNum    当前显示页码
     * @param pageSize   每页显示数量
     * @param orderBy    排序规则
     * @return 返回服务器响应，分页结果
     */
    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);
}
