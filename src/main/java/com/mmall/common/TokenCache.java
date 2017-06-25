package com.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 项目：  mmall
 * 包名：  com.mmall.common
 * 作者：  chencong
 * 时间：  2017/5/26 14:03.
 * 描述：  UUID生成的token
 */
public class TokenCache {

    private static Logger looger = LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX = "token_";
    /**
     * LRU算法
     * 默认的数据加载实现，当调用get取值的时候，如果key没有对应的值，就调用这个方法进行加载
     */
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000)
            .maximumSize(10000)
            .expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                //默认的数据加载实现，当调用get取值的时候，如果key没有对应的值，就调用这个方法进行加载
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    /**
     * set存储cache
     *
     * @param key   key
     * @param value value
     */
    public static void setKey(String key, String value) {
        localCache.put(key, value);
    }

    /**
     * get方法 cache
     *
     * @param key key
     * @return
     */
    public static String getKey(String key) {
        String value = null;
        try {
            value = localCache.get(key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (Exception e) {
            looger.error("localCache get error ", e);
            return null;
        }
    }
}
