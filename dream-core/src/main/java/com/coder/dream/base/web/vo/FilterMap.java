package com.coder.dream.base.web.vo;

import com.coder.dream.base.dao.query.DynamicQuery;
import com.coder.dream.base.dao.query.support.DefaultDynamicQuery;
import com.coder.dream.base.dao.query.support.FilterParam;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 过滤条件map
 *
 */
public class FilterMap extends LinkedHashMap<FilterParam,Object>{
    /**
     * 等于
     *
     * @param column
     * @param value
     */
    public void eq(String column,Object value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_EQUAL);
        this.put(param,value);
    }

    /**
     * 不等
     *
     * @param column
     * @param value
     */
    public void ne(String column,Object value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_NOT_EQUAL);
        this.put(param,value);
    }

    /**
     * 大于
     *
     * @param column
     * @param value
     */
    public void gt(String column,Integer value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_GREATER);
        this.put(param,value);
    }

    /**
     * 大于
     *
     * @param column
     * @param value
     */
    public void gt(String column,Date value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_GREATER);
        this.put(param,value);
    }

    /**
     * 大于等于
     *
     * @param column
     * @param value
     */
    public void ge(String column,Integer value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_GREATER_EQUAL);
        this.put(param,value);
    }

    /**
     * 大于等于
     *
     * @param column
     * @param value
     */
    public void ge(String column,Date value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_GREATER_EQUAL);
        this.put(param,value);
    }

    /**
     * 小于
     *
     * @param column
     * @param value
     */
    public void lt(String column,Integer value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_LESS);
        this.put(param,value);
    }

    /**
     * 小于
     *
     * @param column
     * @param value
     */
    public void lt(String column,Date value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_LESS);
        this.put(param,value);
    }

    /**
     * 小于等于
     *
     * @param column
     * @param value
     */
    public void le(String column,Integer value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_LESS_EQUAL);
        this.put(param,value);
    }

    /**
     * 小于等于
     *
     * @param column
     * @param value
     */
    public void le(String column,Date value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_LESS_EQUAL);
        this.put(param,value);
    }

    /**
     * like
     *
     * @param column
     * @param value
     */
    public void like(String column,String value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_LIKE);
        this.put(param,value);
    }

    /**
     * not like
     *
     * @param column
     * @param value
     */
    public void notLike(String column,String value){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_NOT_LIKE);
        this.put(param,value);
    }

    /**
     * in
     *
     * @param column
     * @param values
     */
    public void in(String column,List<String> values){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_IN);
        this.put(param,values);
    }

    /**
     * in
     *
     * @param column
     * @param values
     */
    public void in(String column,String[] values){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_IN);
        this.put(param,values);
    }

    /**
     * not in
     *
     * @param column
     * @param values
     */
    public void notIn(String column,List<String> values){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_NOT_IN);
        this.put(param,values);
    }

    /**
     * or
     *
     * @param subFilters
     */
    public void or(DefaultDynamicQuery subFilters){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_OR,
                DynamicQuery.LOGIC_OR_PLACEHOLDER + this.size(),
                DynamicQuery.LOGIC_OR_PLACEHOLDER + this.size());
        this.put(param, subFilters.getFilters());
    }

    /**
     * 为空
     *
     * @param column
     */
    public void isNull(String column){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_IS);
        this.put(param,null);
    }

    public void isNotNull(String column){
        FilterParam param = new FilterParam(DynamicQuery.LOGIC_AND,column,DynamicQuery.OPERATOR_IS_NOT);
        this.put(param,null);
    }
}
