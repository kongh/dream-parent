package com.coder.dream.base.service;

import com.coder.dream.base.dao.BaseDao;
import com.coder.dream.base.dao.domain.Page;
import com.coder.dream.base.dao.mapper.BaseMapper;
import com.coder.dream.base.dao.model.BaseEntity;
import com.coder.dream.base.web.vo.BaseVo;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BaseService<T extends BaseEntity,M extends BaseMapper<T>,D extends BaseDao<T,M>> {

    @Autowired
    protected D dao;

    /**
     * 列表
     *
     * @param filterMap
     * @param orderMap
     * @return
     */
    public List<T> list(FilterMap filterMap,OrderMap orderMap){
        return dao.list(filterMap,orderMap);
    }

    /**
     * 列表
     *
     * @param filterMap
     * @param orderMap
     * @return
     */
    public <V extends BaseVo> List<V> list(FilterMap filterMap,OrderMap orderMap,Class<V> clazz){
        List<V> voList = new ArrayList<V>();
        List<T> list= dao.list(filterMap,orderMap);
        for (T t : list){
            V v = null;
            try{
                v = clazz.newInstance();
            }catch (Exception e){
                e.printStackTrace();
            }
            toListVo(t,v);
            voList.add(v);
        }
        return voList;
    }

    /**
     * 转换成列表vo
     *
     * @param t
     * @param vo
     */
    protected void toListVo(T t, BaseVo vo){

    }

    /**
     * 分页
     *
     * @param filterMap
     * @param orderMap
     * @param pageIndex
     *         第几页
     * @param pageLimit
     *         每页限制
     * @return
     */
    public Page<T> page(FilterMap filterMap,OrderMap orderMap,Integer pageIndex,Integer pageLimit){
        return dao.page(filterMap,orderMap,pageIndex,pageLimit);
    }
}
