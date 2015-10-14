package com.coder.dream.base.data.cache;

/**
 * 缓存接口
 */
public interface ICache {

    /**
     * 获取缓存的提供者
     *
     * @param <T>
     * @return
     */
    public <T> T getCachedProvider();
}
