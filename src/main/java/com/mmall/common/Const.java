package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 项目：  mmall
 * 包名：  com.mmall.common
 * 作者：  chencong
 * 时间：  2017/5/25 23:41.
 * 描述：  常量类
 */
public class Const {

    //session 的键
    public static final String CURRENT_USER = "currentUser";

    //type类别信息
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    /**
     * 商品列表排序规则接口
     */
    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }

    /**
     * 购物车是否选中状态
     * CHECKED = 1  购物车选中状态
     * UN_CHECKED = 0 购物车中未选中状态
     * <p>限制产品数量，在添加商品进入购物车时,判断库存是否大于将要添加进入购物车的数量</p>
     * LIMIT_NUM_FAIL ---> limit_num_fail，添加失败
     * <p>LIMIT_NUM_SUCCESS ---> limit_num_success ，添加成功</p>
     */
    public interface Cart {
        int CHECKED = 1;  //购物车选中状态
        int UN_CHECKED = 0; //购物车中未选中状态

        String LIMIT_NUM_FAIL = "limit_num_fail";
        String LIMIT_NUM_SUCCESS = "limit_num_success";
    }

    /**
     * 角色接口类
     */
    public interface Role {
        int ROLE_USTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;   //管理员
    }

    /**
     * 定义商品状态枚举
     */
    public enum ProductStatusEnum {
        ON_SALE(1, "在线销售");
        private String value;
        private int code;

        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }
}
