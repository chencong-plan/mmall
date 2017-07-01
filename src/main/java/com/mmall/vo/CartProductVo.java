package com.mmall.vo;

import java.math.BigDecimal;

/**
 * 项目：  mmall
 * 包名：  com.mmall.vo
 * 作者：  chencong
 * 时间：  2017/7/1 16:47.
 * 描述：  结合产品product和购物车cart进行抽象的一个view object
 * 购物车商品vo
 */
public class CartProductVo {

    private Integer id;  //购物车ID
    private Integer userId; //用户ID
    private Integer productId; //商品ID
    private Integer quantity; //购物车中产品的数量

    private String productName; //产品名称
    private String productSubtitle; //产品标题
    private String productMainImage; //产品主图
    private BigDecimal productPrice; //产品价格
    private Integer productStatus; //产品状态
    private BigDecimal productTotalPrice; //产品总价
    private Integer productStock; //产品库存
    private Integer productChecked; //此商品是否勾选

    private String limitQuantity;//限制数量的一个返回结果

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSubtitle() {
        return productSubtitle;
    }

    public void setProductSubtitle(String productSubtitle) {
        this.productSubtitle = productSubtitle;
    }

    public String getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(String productMainImage) {
        this.productMainImage = productMainImage;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getProductChecked() {
        return productChecked;
    }

    public void setProductChecked(Integer productChecked) {
        this.productChecked = productChecked;
    }

    public String getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(String limitQuantity) {
        this.limitQuantity = limitQuantity;
    }
}
