package com.coder.dream.base.dao.query.support;

import com.coder.dream.base.dao.query.DynamicQuery;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;

/**
 * 动态查询实现
 *
 * <p>
 *     动态条件查询
 * </p>
 */
public class DefaultDynamicQuery implements DynamicQuery {

    private FilterMap filters;

    private OrderMap orders;

    private PageParam pageParam;

    public DefaultDynamicQuery(){
    }

    public DefaultDynamicQuery(FilterMap filterMap){
        this.filters = filterMap;
    }

    public DefaultDynamicQuery(FilterMap filterMap,OrderMap orderMap){
        this.filters = filterMap;
        this.orders = orderMap;
    }

    public DefaultDynamicQuery(FilterMap filterMap,OrderMap orderMap,Integer pageIndex,Integer pageLimit){
        this.filters = filterMap;
        this.orders = orderMap;
        pageParam = new PageParam();
        pageParam.setStart((pageIndex - 1) * pageLimit);
        pageParam.setLimit(pageLimit);
    }

    public FilterMap getFilters() {
        return filters;
    }

    public OrderMap getOrders() {
        return orders;
    }

    public PageParam getPageParam() {
        return pageParam;
    }
}
