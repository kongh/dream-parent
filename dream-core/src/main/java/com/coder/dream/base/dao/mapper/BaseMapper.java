package com.coder.dream.base.dao.mapper;

import com.coder.dream.base.dao.model.BaseEntity;
import com.coder.dream.base.dao.query.Query;

import java.util.List;

public interface BaseMapper<T extends BaseEntity> {

    /**
     * 列表
     *
     * @param query
     * @return
     */
    public List<T> list(Query query);

    /**
     * 查找ID列表
     *
     * @param query
     * @return
     */
    public List<Integer> findIds(Query query);

    /**
     * 查找一个
     *
     * @param query
     * @return
     */
    public T findOne(Query query);

    /**
     * 插入
     *
     * @param t
     * @return row数量
     */
    public int insert(T t);

    /**
     * 更新
     *
     * @param t
     */
    public int update(T t);

    /**
     * 删除一个
     *
     * @param id
     */
    public void deleteOne(String id);

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteBatch(List<String> ids);

    /**
     * 数量
     *
     * @param query
     * @return
     */
    public int count(Query query);
}
