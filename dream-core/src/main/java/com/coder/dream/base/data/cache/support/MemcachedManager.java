package com.coder.dream.base.data.cache.support;

import com.coder.dream.base.data.cache.IEntityCache;
import net.rubyeye.xmemcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Memcached缓存管理器
 *
 */
public class MemcachedManager implements IEntityCache{

    @Autowired
    private MemcachedClient mc;

    //日志
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void writeOneIntoCache(String keyPrefix, Integer id, int exp, Object caching) {
        try{
            mc.set(String.format("%s_%d", keyPrefix, id),exp,caching);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public <T> T readOneFromCache(String keyPrefix, Integer id) {
        T cached = null;
        try{
            cached =  mc.get(String.format("%s_%d",keyPrefix,id));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return cached;
    }

    public <T> T getCachedProvider() {
        return (T)mc;
    }
}
