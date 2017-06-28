package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * 项目：  mmall
 * 包名：  com.mmall.service
 * 作者：  chencong
 * 时间：  2017/6/28 10:35.
 * 描述：  分类结点接口
 */
public interface ICategoryService {

    /**
     * 添加商品品类接口
     *
     * @param categoryName 品类名称
     * @param parentId     父节点ID
     * @return 返回服务器响应
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 更新商品品类的接口
     *
     * @param categoryId   待更新商品ID
     * @param categoryName 待更新的商品品类名称
     * @return 返回服务器响应
     */
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    /**
     * 根据categoryId查找该分类的子类
     *
     * @param categoryId 待查找的categoryId
     * @return 该分类下的子类list信息
     */
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    /**
     * 递归查询本节点的ID及所有子节点ID
     *
     * @param categoryId 待查询的结点ID
     * @return 返回相应list
     */
    ServerResponse selectCategoryAndChildrenById(Integer categoryId);
}
