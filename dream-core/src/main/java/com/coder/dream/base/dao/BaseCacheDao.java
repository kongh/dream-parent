package com.coder.dream.base.dao;

import com.coder.dream.base.dao.domain.Page;
import com.coder.dream.base.dao.mapper.BaseMapper;
import com.coder.dream.base.dao.model.BaseEntity;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by Administrator on 2015/9/29.
 */
public abstract class BaseCacheDao<T extends BaseEntity,M extends BaseMapper<T>> extends BaseDao<T,M>{

    @Autowired
    private MemcachedClient mc;

    @Override
    public T create(T t) {
        T created = super.create(t);
        writeOneIntoCache(created);
        return created;
    }

    /**
     * 缓存一个实体
     *
     * @param caching
     */
    protected void writeOneIntoCache(T caching){
        try{
            mc.set(getCacheEntityKey(caching.getId()),getCacheExpirationTime(),caching);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public T findOne(Integer id) {
        T cached = readOneFromCache(id);
        if(cached != null){
            return cached;
        }

        T one = super.findOne(id);
        writeOneIntoCache(one);
        return one;
    }

    @Override
    public List<T> list(FilterMap filterMap, OrderMap orderMap) {
        List<Integer> ids = findIds(filterMap, orderMap);
        if(CollectionUtils.isEmpty(ids)){
            return Collections.emptyList();
        }

        //未命中缓存记录，key=id,value=index
        Map<Integer,Integer> notHits = new HashMap<Integer,Integer>();

        List<T> entities = new ArrayList<T>(ids.size());
        for(int i = 0; i < ids.size(); ++i){
            Integer id = ids.get(i);
            T cached = readOneFromCache(id);
            if(cached == null){
                notHits.put(id,i);
            }
            entities.add(cached);
        }

        if(!notHits.isEmpty()){
            //修复未命中缓存的记录
            FilterMap fixHitMap = new FilterMap();
            Integer[] notHitsIds = new Integer[notHits.keySet().size()];
            notHits.keySet().toArray(notHitsIds);
            fixHitMap.in("id", notHitsIds);
            List<T> list = super.list(fixHitMap, null);
            for (T t : list){
                Integer id = t.getId();
                Integer notHitIndex = notHits.get(id);
                entities.set(notHitIndex,t);
                writeOneIntoCache(t);
            }
        }

        return entities;
    }

    @Override
    public Page<T> page(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit) {
        return super.page(filterMap, orderMap, pageIndex, pageLimit);
    }

    /**
     * 读取一个被缓存的实体
     *
     * @param id
     * @return
     */
    protected T readOneFromCache(Integer id){
        T cached = null;
        try{
            cached =  mc.get(getCacheEntityKey(id));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return cached;
    }

    /**
     * 获取准备缓存实体的键
     *
     * @param id
     * @return
     */
    protected String getCacheEntityKey(Integer id){
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getSimpleName());
        buffer.append("_");
        buffer.append(id);
        return buffer.toString();
    }

    /**
     * 获取缓存超时时间
     *
     * 单位：秒
     * @return
     */
    protected int getCacheExpirationTime(){
        return 30;
    }
}
