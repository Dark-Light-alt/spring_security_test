package com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页数据
 */
public class Page {

    //当前页
    private Integer currentPage = 1;

    //每页多少条
    private Integer pageSize = 5;

    //总共多少条数据
    private Long total;

    //尾页
    private Integer lastPage;

    //搜索内容
    private Map<String, String> searchs = new HashMap<>();

    //是否启用
    private boolean flag = true;

    public Page() {
    }

    public Page(boolean flag) {
        this.flag = flag;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Map<String, String> getSearchs() {
        return searchs;
    }

    public void setSearchs(Map<String, String> searchs) {
        this.searchs = searchs;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", lastPage=" + lastPage +
                ", searchs='" + searchs + '\'' +
                ", flag=" + flag +
                '}';
    }
}
