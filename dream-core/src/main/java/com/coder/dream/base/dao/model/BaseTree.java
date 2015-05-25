package com.coder.dream.base.dao.model;

/**
 * 基础树实体
 */
public abstract class BaseTree extends BaseEntity{

    protected String parentId;

    protected transient Boolean leaf;

    protected transient Boolean expanded;

    protected transient Object data;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
