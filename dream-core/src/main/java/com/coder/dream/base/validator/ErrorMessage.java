package com.coder.dream.base.validator;

import java.io.Serializable;

/**
 * Created by konghang on 15-6-14.
 */
public class ErrorMessage implements Serializable{

    private String param;

    private String error;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(param);
        buffer.append("[");
        buffer.append(error);
        buffer.append("]");
        return buffer.toString();
    }
}
