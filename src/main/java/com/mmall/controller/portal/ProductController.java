package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.service.IProductService;
import com.mmall.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 项目：  mmall
 * 包名：  com.mmall.controller.portal
 * 作者：  chencong
 * 时间：  2017/6/30 11:02.
 * 描述：  前台部分---商品详情
 */

@Controller
@RequestMapping("/product/")
public class ProductController {


    @Autowired
    private IProductService iProductService;

    /**
     * 前台部分展示商品详情，首先需要查看产品状态是否处于在线销售状态
     *
     * @param productId 将要被展示的商品ID
     * @return 返回服务器响应
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId) {
        return iProductService.getProductDetail(productId);
    }

    /**
     * 前台
     * 根据关键字或者分类ID搜索商品信息，并且按照指定排序规则进行排序
     * 结果以分页形式返回
     *
     * @param keyword    搜索关键字
     * @param categoryId 搜索产品分类ID
     * @param pageNum    当前显示页码 默认1
     * @param pageSize   每页显示数量 默认10
     * @param orderBy    按照什么关键字排序/升序或者降序---price-Desc
     * @return 返回服务器响应
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
        return iProductService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize, orderBy);
    }
}
