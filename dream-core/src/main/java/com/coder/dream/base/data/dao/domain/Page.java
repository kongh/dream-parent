package com.coder.dream.base.data.dao.domain;

 /**
 * A page is a sublist of a list of objects. It allows gain information about the position of it in the containing
 * entire list.
 *
 * @param <T>
 * @author Oliver Gierke
 */
public interface Page<T> extends Slice<T> {

    /**
     * Returns the number of total pages.
     *
     * @return the number of toral pages
     */
    int getTotalPages();

    /**
     * Returns the total amount of elements.
     *
     * @return the total amount of elements
     */
    long getTotalElements();
}
