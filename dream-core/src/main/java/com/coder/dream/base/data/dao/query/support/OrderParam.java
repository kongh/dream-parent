package com.coder.dream.base.data.dao.query.support;

/**
 * 排序参数
 *
 */
public class OrderParam {

    /**
     * 列名
     */
    private String column;

    /**
     * 方向
     */
    private String direction;


    /**
     * 构造
     *
     */
    public OrderParam(){

    }

    /**
     * 构造方法
     *
     * @param column
     * @param direction
     */
    public OrderParam(String column, String direction) {
        this.column = column;
        this.direction = direction;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }

        if(!(obj instanceof OrderParam)){
            return false;
        }

        OrderParam compare = (OrderParam)obj;
        return column == compare.getColumn() &&
                direction == compare.getDirection();
    }

    @Override
    public int hashCode() {
        return column.hashCode() ^ direction.hashCode();
    }
}
