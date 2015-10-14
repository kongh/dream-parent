package com.coder.dream.base.data.dao;

import com.coder.dream.base.data.cache.ICache;
import com.coder.dream.base.data.cache.IEntityCache;
import com.coder.dream.base.data.dao.domain.Page;
import com.coder.dream.base.data.dao.domain.PageImpl;
import com.coder.dream.base.data.dao.mapper.BaseMapper;
import com.coder.dream.base.data.dao.model.BaseEntity;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by Administrator on 2015/9/29.
 */
public abstract class BaseCacheDao<T extends BaseEntity,M extends BaseMapper<T>> extends BaseDao<T,M>{

    /**
     * 实体缓存策略
     */
    protected IEntityCache entityCached;

    /**
     * 设置缓存
     * @param entityCached
     */
    public abstract void setCachedManger(IEntityCache entityCached);

    /**
     * 创建
     *
     * @param t
     * @param cacheEnable true:需缓存 false:无需缓存
     * @return
     */
    public T create(T t,Boolean cacheEnable){
        T created = super.create(t);
        if(cacheEnable){
            entityCached.writeOneIntoCache(getCacheEntityPrefixKey(),created.getId(),getCacheExpirationTime(),created);
        }
        return created;
    }

    /**
     * 更新
     *
     * @param t
     * @param cacheEnable true:需缓存 false:无需缓存
     * @return
     */
    public int update(T t,Boolean cacheEnable) {
        int rows = super.update(t);
        if(rows > 0 && cacheEnable){
            T one = super.findOne(t.getId());
            entityCached.writeOneIntoCache(getCacheEntityPrefixKey(), t.getId(), getCacheExpirationTime(), one);
        }
        return rows;
    }

    /**
     * 查找一个
     *
     * @param id
     * @param cacheEnable
     * @return
     */
    public T findOne(Integer id,Boolean cacheEnable) {
        if(cacheEnable){
            T cached = entityCached.readOneFromCache(getCacheEntityPrefixKey(),id);
            if(cached != null){
                return cached;
            }
        }

        T one = super.findOne(id);

        if(one == null){
            return null;
        }

        if(cacheEnable){
            entityCached.writeOneIntoCache(getCacheEntityPrefixKey(),one.getId(),getCacheExpirationTime(),one);
        }
        return one;
    }

    /**
     * 列表
     *
     * @param filterMap
     * @param orderMap
     * @param cacheEnable true:需缓存 false:无需缓存
     * @return
     */
    public List<T> list(FilterMap filterMap, OrderMap orderMap,Boolean cacheEnable) {
        if(!cacheEnable){
            super.list(filterMap, orderMap);
        }
        List<Integer> ids = findIds(filterMap, orderMap);
        return findByIds(ids);
    }

    protected List<T> findByIds(List<Integer> ids){
        if(CollectionUtils.isEmpty(ids)){
            return Collections.emptyList();
        }

        //未命中缓存记录，key=id,value=index
        Map<Integer,Integer> notHits = new HashMap<Integer,Integer>();

        List<T> entities = new ArrayList<T>(ids.size());
        for(int i = 0; i < ids.size(); ++i){
            Integer id = ids.get(i);
            T cached = entityCached.readOneFromCache(getCacheEntityPrefixKey(),id);
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
            List<T> list = super.list(fixHitMap);
            for (T t : list){
                Integer id = t.getId();
                Integer notHitIndex = notHits.get(id);
                entities.set(notHitIndex,t);
                entityCached.writeOneIntoCache(getCacheEntityPrefixKey(), id, getCacheExpirationTime(), t);
            }
        }
        return entities;
    }

    /**
     * 分页列表
     *
     * @param filterMap
     * @param orderMap
     * @param pageIndex
     * @param pageLimit
     * @param cacheEnable true:需缓存 false:无需缓存
     * @return
     */
    public Page<T> page(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit,Boolean cacheEnable) {
        if(!cacheEnable){
            return super.page(filterMap, orderMap, pageIndex, pageLimit);
        }

        //查询数量
        int total = count(filterMap);

        //填写结果
        Page<T> page = null;
        if(total == 0){
            page = new PageImpl<T>(new ArrayList<T>(),pageIndex,pageLimit,0);
        }else{
            List<Integer> ids = findIds(filterMap, orderMap, pageIndex, pageLimit);
            page = new PageImpl<T>(findByIds(ids),pageIndex,pageLimit,total);
        }
        return page;
    }

    /**
     * 获取准备缓存实体的键前缀值
     *
     */
    protected String getCacheEntityPrefixKey(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getSimpleName());
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
