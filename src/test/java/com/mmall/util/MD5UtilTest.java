package com.mmall.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 项目：  mmall
 * 包名：  com.mmall.util
 * 作者：  chencong
 * 时间：  2017/6/25 17:20.
 * 描述：  测试密码工具类
 */
public class MD5UtilTest {
    @Test
    public void MD5EncodeUtf8() throws Exception {
        assertEquals("E10ADC3949BA59ABBE56E057F20F883E", MD5Util.MD5EncodeUtf8("123456"));
    }

}