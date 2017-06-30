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
