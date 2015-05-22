package com.coder.dream.base.dao.query;

import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import com.coder.dream.base.dao.query.support.PageParam;

/**
 * 动态查询接口
 *
 */
public interface DynamicQuery extends Query{
    /**
     * 逻辑运算符（and）
     */
    public final static String LOGIC_AND = "and";

    /**
     * 逻辑运算符（or）
     */
    public final static String LOGIC_OR = "or";

    /**
     * 逻辑运算符（or）占位符
     */
    public final static String LOGIC_OR_PLACEHOLDER ="LOGIC_OR_PLACEHOLDER";

    /**
     * 操作符（=）
     */
    public final static String OPERATOR_EQUAL = "=";

    /**
     * 操作符（！=）
     */
    public final static String OPERATOR_NOT_EQUAL = "!=";

    /**
     * 操作符（like）
     */
    public final static String OPERATOR_LIKE = "like";

    /**
     * 操作符（not like）
     */
    public final static String OPERATOR_NOT_LIKE = "not like";

    /**
     * 操作符（in）
     */
    public final static String OPERATOR_IN = "in";

    /**
     * 操作符（not in）
     */
    public final static String OPERATOR_NOT_IN = "not in";

    /**
     * 操作符（大于）
     */
    public final static String OPERATOR_GREATER = ">";

    /**
     * 操作符（大于等于）
     */
    public final static String OPERATOR_GREATER_EQUAL = ">=";

    /**
     * 操作符（小于）
     */
    public final static String OPERATOR_LESS = "<";

    /**
     * 操作符（小于等于）
     */
    public final static String OPERATOR_LESS_EQUAL = "<=";

    /**
     * 操作符（is）
     */
    public final static String OPERATOR_IS = "is";

    /**
     * 操作符（is not null）
     */
    public final static String OPERATOR_IS_NOT = "is not";

    /**
     * 排序方向（顺序）
     */
    public final static String ORDER_DIRECTION_ASC = "asc";

    /**
     * 排序方向（倒序）
     */
    public final static String ORDER_DIRECTION_DESC = "desc";

    /**
     * 过滤条件列表
     *
     * @return
     */
    public FilterMap getFilters();

    /**
     * 排序条件列表
     *
     * @return
     */
    public OrderMap getOrders();

    /**
     * 分页参数
     *
     * @return
     */
    public PageParam getPageParam();
}
