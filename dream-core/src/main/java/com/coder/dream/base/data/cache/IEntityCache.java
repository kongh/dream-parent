package com.coder.dream.base.data.cache;

/**
 * 实体缓存接口
 */
public interface IEntityCache extends ICache{

    /**
     * 缓存一个实体
     *
     * @param keyPrefix
     * @param id
     * @param exp
     * @param caching
     */
    public void writeOneIntoCache(final String keyPrefix,Integer id, final int exp, final Object caching);

    /**
     * 读取一个被缓存的实体
     *
     * @param keyPrefix
     * @param id
     * @param <T>
     * @return
     */
    public <T> T readOneFromCache(final String keyPrefix,Integer id);
}
