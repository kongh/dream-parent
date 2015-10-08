package com.coder.dream.base.dao;

import com.coder.dream.base.dao.domain.Page;
import com.coder.dream.base.dao.domain.PageImpl;
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
            writeOneIntoCache(created);
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
            writeOneIntoCache(findOne(t.getId()));
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
            T cached = readOneFromCache(id);
            if(cached != null){
                return cached;
            }
        }

        T one = super.findOne(id);

        if(cacheEnable){
            writeOneIntoCache(one);
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
