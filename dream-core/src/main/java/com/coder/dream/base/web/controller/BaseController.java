package com.coder.dream.base.web.controller;

import com.coder.dream.base.dao.BaseDao;
import com.coder.dream.base.dao.mapper.BaseMapper;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import com.coder.dream.base.dao.model.BaseEntity;
import com.coder.dream.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

public abstract class BaseController<T extends BaseEntity,M extends BaseMapper<T>,D extends BaseDao<T,M>,S extends BaseService<T,M,D>>{

    @Autowired
    protected S service;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List list(@RequestParam Map<String,String> params){
        FilterMap filterMap = new FilterMap();
        OrderMap orderMap = new OrderMap();
        beforeList(params,filterMap,orderMap);
        return service.list(filterMap,orderMap);
    }

    /**
     * 列表前
     *
     * @param params
     */
    protected void beforeList(Map<String,String> params,FilterMap filterMap,OrderMap orderMap){

    }

}
