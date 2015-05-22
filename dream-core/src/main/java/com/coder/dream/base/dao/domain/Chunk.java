package com.coder.dream.base.dao.domain;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

abstract class Chunk<T> implements Slice<T>, Serializable {

    private static final long serialVersionUID = 867755909294344406L;

    private final List<T> content = new ArrayList<T>();
    private Integer pageNum = 0;
    private Integer pageSize = 0;

    public Chunk(List<T> content, Integer pageNum,Integer pageSize) {

        Assert.notNull(content, "Content must not be null!");

        this.content.addAll(content);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getNumber() {
        return pageNum;
    }

    public int getSize() {
       return pageSize;
    }

    public int getNumberOfElements() {
        return content.size();
    }

    public boolean hasPrevious() {
        return getNumber() > 0;
    }

    public boolean isFirst() {
        return !hasPrevious();
    }

    public boolean isLast() {
        return !hasNext();
    }

    public boolean hasContent() {
        return !content.isEmpty();
    }

    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<T> iterator() {
        return content.iterator();
    }

}