package com.coder.dream.base.web.vo;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultMap extends LinkedHashMap<String, Object> {

    private static final String SUCCESS = "success";

    private static final String DATA = "data";

    private static final String MSG = "msg";

    private static final String TOTAL = "total";

    /**
     * ��ȡ�Ƿ�ɹ�
     *
     * @return
     */
    public Boolean getSuccess() {
        return (Boolean) this.get(SUCCESS);
    }

    private void setSuccess(Boolean success) {
        this.put(SUCCESS, success);
    }

    /**
     * ��ȡ��������
     *
     * @return
     */
    public Object getData() {
        return this.get(DATA);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getDataMap() {
        return (Map<String, Object>) this.get(DATA);
    }

    private void setData(Object data) {
        this.put(DATA, data);
    }

    /**
     * ��ȡ������Ϣ
     *
     * @return
     */
    public Object getMsg() {
        return this.get(MSG);
    }

    private void setMsg(Object msg) {
        this.put(MSG, msg);
    }

    /**
     * ��ȡ�ܼ�¼��
     *
     * @return
     */
    public Long getTotal() {
        return (Long) this.get(TOTAL);
    }

    private void setTotal(Long total) {
        this.put(TOTAL, total);
    }

    private void setMetaData(Object metaData) {
//		this.put(METADATA, metaData);
    }

    /**
     * ���ؽ���ɹ�����������Ϊnull
     */
    public void success() {
        this.setSuccess(true);
        this.setData(null);
        Map<String, String> metaData = new LinkedHashMap<String, String>();
        metaData.put("successProperty", SUCCESS);
        metaData.put("rootProperty", DATA);
        this.setMetaData(metaData);
    }

    /**
     * ���ؽ���ɹ�����������Ϊdata
     *
     * @param data
     */
    public void success(Object data) {
        this.setSuccess(true);
        this.setData(data);
        Map<String, String> metaData = new LinkedHashMap<String, String>();
        metaData.put("successProperty", SUCCESS);
        metaData.put("rootProperty", DATA);
        this.setMetaData(metaData);
    }

    /**
     * ���ؽ���ɹ�����������Ϊpage
     *
     * @param data
     * @param total
     */
    public void success(Object data, Long total) {
        this.setSuccess(true);
        this.setData(data);
        this.setTotal(total);
        Map<String, String> metaData = new LinkedHashMap<String, String>();
        metaData.put("successProperty", SUCCESS);
        metaData.put("rootProperty", DATA);
        metaData.put("totalProperty", TOTAL);
        this.setMetaData(metaData);
    }

    /**
     * ���ؽ��ʧ�ܣ�������ϢΪnull
     */
    public void failure() {
        this.setSuccess(false);
        this.setMsg(null);
        Map<String, String> metaData = new LinkedHashMap<String, String>();
        metaData.put("successProperty", SUCCESS);
        metaData.put("messageProperty", MSG);
        this.setMetaData(metaData);
    }

    /**
     * ���ؽ��ʧ�ܣ�������ϢΪmsg
     *
     * @param msg
     */
    public void failure(Object msg) {
        this.setSuccess(false);
        this.setMsg(msg);
        Map<String, String> metaData = new LinkedHashMap<String, String>();
        metaData.put("successProperty", SUCCESS);
        metaData.put("messageProperty", MSG);
        this.setMetaData(metaData);
    }

}
