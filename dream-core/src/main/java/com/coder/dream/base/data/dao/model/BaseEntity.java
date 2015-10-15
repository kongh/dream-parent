package com.coder.dream.base.data.dao.model;

import java.util.Date;
/**
 * 基础实体
 */
public abstract class BaseEntity extends IdEntity {

    protected Date createTime;

    protected Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
