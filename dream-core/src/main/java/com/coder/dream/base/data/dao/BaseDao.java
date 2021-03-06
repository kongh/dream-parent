package com.coder.dream.base.data.dao;

import com.coder.dream.base.data.dao.domain.Page;
import com.coder.dream.base.data.dao.domain.PageImpl;
import com.coder.dream.base.data.dao.mapper.BaseMapper;
import com.coder.dream.base.data.dao.model.BaseEntity;
import com.coder.dream.base.data.dao.query.Query;
import com.coder.dream.base.data.dao.query.support.DefaultDynamicQuery;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T extends BaseEntity,M extends BaseMapper<T>> {

    @Autowired
    protected M mapper;

    //日志
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 创建
     *
     * @param t
     * @return row数
     */
    public T create(T t){
        mapper.insert(t);
        return t;
    }

    /**
     * 修改
     *
     * @param t
     * @return 受影响的行数
     */
    public int update(T t){
        return mapper.update(t);
    }

    /**
     * 查找一个
     *
     * @param id
     * @return
     */
    public T findOne(Integer id){
        FilterMap filterMap = new FilterMap();
        filterMap.eq("id", id);
        return findOne(filterMap);
    }

    /**
     * 查找一个
     *
     * @return
     */
    public T findOne(FilterMap filterMap){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,null,false);
        return findOne(query);
    }

    /**
     * 查找一个
     *
     * @return
     */
    public T findOne(FilterMap filterMap,Boolean rowLock){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,null,rowLock);
        return findOne(query);
    }

    /**
     * 查找一个
     *
     * @param query
     * @return
     */
    public T findOne(Query query){
        return mapper.findOne(query);
    }

    /**
     * 列表
     *
     * @param filterMap
     * @return
     */
    public List<T> list(FilterMap filterMap){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,null,false);
        return list(query);
    }

    /**
     * 列表
     *
     * @param filterMap
     * @param orderMap
     * @return
     */
    public List<T> list(FilterMap filterMap,OrderMap orderMap){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,false);
        return list(query);
    }

    /**
     * 列表
     *
     * @param query
     * @return
     */
    public List<T> list(Query query){
        return mapper.list(query);
    }

    /**
     * 查找ID列表
     *
     * @param filterMap
     * @param orderMap
     * @return
     */
    public List<Integer> findIds(FilterMap filterMap,OrderMap orderMap){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,null);
        return mapper.findIds(query);
    }

    /**
     * 查找ID分页列表
     *
     * @param filterMap
     * @param orderMap
     * @return
     */
    public List<Integer> findIds(FilterMap filterMap,OrderMap orderMap,int pageIndex,int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        return mapper.findIds(query);
    }

    /**
     * 分页
     *
     * @param filterMap
     * @param orderMap
     * @param pageIndex
     * @param pageLimit
     * @return
     */
    public Page<T> page(FilterMap filterMap,OrderMap orderMap,int pageIndex,int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        //查询数量
        int total = count(filterMap);

        //填写结果
        Page<T> page = null;
        if(total == 0){
            page = new PageImpl<T>(new ArrayList<T>(),pageIndex,pageLimit,0);
        }else{
            page = new PageImpl<T>(mapper.list(query),pageIndex,pageLimit,total);
        }
        return page;
    }

    /**
     * 数量
     *
     * @param filterMap
     * @return
     */
    public int count(FilterMap filterMap){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,null,false);
        return count(query);
    }

    /**
     * 数量
     *
     * @param query
     * @return
     */
    public int count(Query query){
        return mapper.count(query);
    }
}
