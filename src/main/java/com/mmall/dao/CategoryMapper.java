package com.mmall.dao;

import com.mmall.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 根据categoryid查询子节点的category信息
     *
     * @param parentId 待查询的categoryId
     * @return list<Category>
     */
    List<Category> selectCategoryChildrenByParentId(Integer parentId);
}