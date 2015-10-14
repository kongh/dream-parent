package com.coder.dream.base.web.vo;

import com.coder.dream.base.data.dao.query.DynamicQuery;
import com.coder.dream.base.data.dao.query.support.OrderParam;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2015/4/20.
 */
public class OrderMap extends LinkedHashMap<OrderParam,Object>{
    /**
     * ˳��
     *
     * @param column
     */
    public void asc(String column) {
        OrderParam order = new OrderParam(column, DynamicQuery.ORDER_DIRECTION_ASC);
        this.put(order, null);
    }

    /**
     * ����
     *
     * @param column
     */
    public void desc(String column) {
        OrderParam order = new OrderParam(column, DynamicQuery.ORDER_DIRECTION_DESC);
        this.put(order, null);
    }
}
