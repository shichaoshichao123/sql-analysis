package com.souche.sql.analysis.helper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: shichao
 * @date: 2019/3/27
 * @description: 基于LinkedHashMap实现的本地LRU缓存
 * 使用方式：由于涉及到相关的不同类，所有在放置缓存的时候缓存的key请使用 接口名称+方法名称+参数值 的形式，以避免缓存的误读和串读。
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {

    private volatile static LruCache Instance = null;

    /**
     * 本地缓存容量
     */
    private int initialCapacity = 100;
    /**
     * 扩容因子
     */
    private static float LOAD_FACTOR = 0.75F;
    /**
     * 表示会根据访问顺序调整元素内部顺序
     */
    private static boolean ACCESS_ORDER = true;

    /**
     * 默认容量的初始化函数
     */
    private LruCache() {
        super(16, LOAD_FACTOR, ACCESS_ORDER);
    }

    /**
     * 可指定缓存数的构造函数
     *
     * @param initialCapacity
     */
    private LruCache(int initialCapacity) {
        super(initialCapacity, LOAD_FACTOR, ACCESS_ORDER);
        this.initialCapacity = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > initialCapacity;
    }

    /**
     * 单例存在
     * @param initialCapacity
     * @return
     * @throws InterruptedException
     */
    public static LruCache getInstance(int initialCapacity) throws InterruptedException {
        if (null == Instance) {
            synchronized (LruCache.class) {
                if (null == Instance) {
                    Thread.sleep(1);
                    Instance = new LruCache(initialCapacity);
                }

            }

        }
        return Instance;
    }

}
