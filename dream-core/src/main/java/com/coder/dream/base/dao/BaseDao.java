package com.coder.dream.base.dao;

import com.coder.dream.base.dao.domain.Page;
import com.coder.dream.base.dao.domain.PageImpl;
import com.coder.dream.base.dao.mapper.BaseMapper;
import com.coder.dream.base.dao.model.BaseEntity;
import com.coder.dream.base.dao.query.Query;
import com.coder.dream.base.dao.query.support.DefaultDynamicQuery;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T extends BaseEntity,M extends BaseMapper<T>> {

    @Autowired
    protected M mapper;

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
     * @return
     */
    public T update(T t){
        mapper.update(t);
        return t;
    }

    /**
     * 查找一个
     *
     * @return
     */
    public T findOne(FilterMap filterMap){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap);
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
     * @param orderMap
     * @return
     */
    public List<T> list(FilterMap filterMap,OrderMap orderMap){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap);
        return mapper.list(query);
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
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap);
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
