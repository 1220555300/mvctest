package com.entity;

import java.util.List;

public class PageData {

    private int totalCount;

    private int currentPage;

    private int currentPageSize;

    private List<?> datas;

    public int getTotalPage(){
        return totalCount/currentPageSize+(totalCount%currentPageSize>0?1:0);
    }

    public void setDatas(List<?> datas) {
        this.datas = datas;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    public int getCurrentPageSize() {
        return currentPageSize;
    }

    public void setCurrentPageSize(int currentPageSize) {
        this.currentPageSize = currentPageSize;
    }

    public List<?> getDatas() {
        return datas;
    }



}
