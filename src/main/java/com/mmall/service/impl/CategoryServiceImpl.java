package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 项目：  mmall
 * 包名：  com.mmall.service.impl
 * 作者：  chencong
 * 时间：  2017/6/28 10:36.
 * 描述：  分类接口的实现类
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    //将categoryMapper注解进来
    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 添加商品品类方法
     *
     * @param categoryName 类别名称
     * @param parentId     类别父节点ID
     * @return 返回服务器响应
     */
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }
        //添加分类
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);  //刚创建的状态 这个分类是可用的

        int rowCount = categoryMapper.insert(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    /**
     * 跟新categoryName
     *
     * @param categoryId   待更新的categoryID
     * @param categoryName 待更新的categoryName
     * @return 返回服务器响应
     */
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        //首先对参数进行判断
        if (categoryId == null && StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        //这里选择updateByPrimaryKeySelective通过主键ID进行有选择性的更新
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("更新品类名称成功");
        }
        return ServerResponse.createBySuccessMessage("更新品类名称失败");
    }

    /**
     * 查询categoryId下的category信息
     *
     * @param categoryId 待查询的category信息
     * @return 返回服务器响应
     */
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            //打印日志
            System.out.println("未找到当前cagegoryId : " + categoryId + "分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    /**
     * 递归查询本节点的ID及子节点ID
     *
     * @param categoryId 待查结点ID
     * @return 结点集合
     */
    public ServerResponse selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            for (Category categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }


    /**
     * 递归算法 算出categoryid的子节点
     * 一定要有一个退出条件
     * 子节点为空时候就退出递归查询
     *
     * @param categorySet 子节点集合
     * @param categoryId  待查子节点的父节点
     * @return 集合
     */
    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //查找子节点 mybatis返回的list中不会存在null 也就不必要进行null判断
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }

}
