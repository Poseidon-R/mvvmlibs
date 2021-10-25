package com.robot.glass.model;

import com.google.gson.JsonArray;

/**
 * Created by Administrator on 2016/12/15.
 */

public class BasePageBean {
    private int pageNum;
    private int pageSize;
    private int total;
    private int pages;
    private boolean hasNextPage;
    private JsonArray list;
    private String categoryName;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public JsonArray getList() {
        return list;
    }

    public void setList(JsonArray list) {
        this.list = list;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
