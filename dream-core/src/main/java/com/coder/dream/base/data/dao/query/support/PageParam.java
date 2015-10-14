package com.coder.dream.base.data.dao.query.support;

/**
 * 分页参数
 *
 */
public class PageParam {

    /**
     * 开始
     */
    private Integer start;

    /**
     * 限制
     */
    private Integer limit;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
