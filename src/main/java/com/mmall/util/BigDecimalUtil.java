package com.mmall.util;

import java.math.BigDecimal;

/**
 * 项目：  mmall
 * 包名：  com.mmall.util
 * 作者：  chencong
 * 时间：  2017/7/1 17:23.
 * 描述：  BigDecimal工具类
 * 在商业运算当中一定要用bigDecimal的String构造
 * <p>由于在实际运算过程中浮点型计算会导致进度丢失，在电商计算价格当中这样会出现很糟糕的问题</p>
 * 将double参数转化成string的，然后调用BigDecimal的String构造器
 * <p>于是在这里封装了BigDecimal的常规加减乘除方法</p>
 */
public class BigDecimalUtil {

    private BigDecimalUtil() {
    }

    /**
     * 加法运算，参数为double
     *
     * @param v1 加数
     * @param v2 被加数
     * @return 返回 v1 + v2
     */
    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    /**
     * 减法运算，参数double
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 返回 v1 - v2
     */
    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    /**
     * 乘法运算,参数double
     *
     * @param v1 乘数
     * @param v2 被乘数
     * @return v1 * v2
     */
    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    /**
     * 除法运算,参数double
     * <p>除不尽的时候四舍五入，保留两位小数</p>
     *
     * @param v1 除数
     * @param v2 被除数
     * @return v1/v2
     */
    public static BigDecimal div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP); //四舍五入，保留两位小数
    }
}
