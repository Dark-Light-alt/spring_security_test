package com.util;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 前后端交互统一响应协议
 */
public class Result {

    //数据头 存放消息和状态信息
    private Map<String, Object> meta = new HashMap<String, Object>();

    //数据体 存放数据信息
    private Map<String, Object> data = new HashMap<String, Object>();

    //存放分页信息
    private Page page;

    public Result() {
    }

    public Result(Map<String, Object> meta, Map<String, Object> data, Page page) {
        this.meta = meta;
        this.data = data;
        this.page = page;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    /**
     * 向数据头中添加信息
     *
     * @param key
     * @param value
     */
    public void putMeta(String key, Object value) {
        this.meta.put(key, value);
    }

    /**
     * 向数据体中添加数据
     *
     * @param key
     * @param value
     */
    public void putData(String key, Object value) {
        this.data.put(key, value);
    }

    /**
     * 访问成功时，返回数据
     *
     * @param statusCode 状态码
     * @param msg        消息
     */
    public void success(Integer statusCode, String msg) {
        this.putMeta("access", Boolean.TRUE);
        this.putMeta("code", statusCode);
        this.putMeta("msg", msg);
        this.putMeta("timestamp", new Timestamp(System.currentTimeMillis()));
    }

    /**
     * 访问失败时，返回数据
     *
     * @param statusCode 状态码
     * @param msg        消息
     */
    public void error(Integer statusCode, String msg) {
        this.putMeta("access", Boolean.FALSE);
        this.putMeta("code", statusCode);
        this.putMeta("msg", msg);
        this.putMeta("timestamp", new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public String toString() {
        return "Result{" +
                "meta=" + meta +
                ", data=" + data +
                ", page=" + page +
                '}';
    }
}
