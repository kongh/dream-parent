package com.coder.dream.base.dao.model;

import java.io.Serializable;

/**
 * Id实体
 */
public abstract class IdEntity implements Serializable{

    //主键
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
