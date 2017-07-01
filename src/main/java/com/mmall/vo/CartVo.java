package com.mmall.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 项目：  mmall
 * 包名：  com.mmall.vo
 * 作者：  chencong
 * 时间：  2017/7/1 16:54.
 * 描述：  购物车view object
 * 购物车vo
 */
public class CartVo {

    private List<CartProductVo> cartProductVoList;  //购物车中的商品vo
    private BigDecimal cartTotalPrice; //购物车总金额
    private Boolean allChecked; //购物车是否全选
    private String imageHost; //图片服务器地址

    public List<CartProductVo> getCartProductVoList() {
        return cartProductVoList;
    }

    public void setCartProductVoList(List<CartProductVo> cartProductVoList) {
        this.cartProductVoList = cartProductVoList;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Boolean getAllChecked() {
        return allChecked;
    }

    public void setAllChecked(Boolean allChecked) {
        this.allChecked = allChecked;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
