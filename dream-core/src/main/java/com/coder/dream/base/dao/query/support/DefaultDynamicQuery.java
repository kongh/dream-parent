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

    private Boolean rowLock;

    public DefaultDynamicQuery(){
    }

    public DefaultDynamicQuery(FilterMap filterMap, OrderMap orderMap,Boolean rowLock){
        this.filters = filterMap;
        this.orders = orderMap;
        this.rowLock = rowLock;
    }

    public DefaultDynamicQuery(FilterMap filterMap, OrderMap orderMap, Integer pageIndex, Integer pageLimit){
        this.filters = filterMap;
        this.orders = orderMap;
        this.rowLock = false;
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

    public Boolean isRowLock() {
        return rowLock;
    }

    public void setRowLock(Boolean rowLock) {
        this.rowLock = rowLock;
    }
}
