package com.example.demo.vo;

public class PageBean {
    //总条数
    private int count;
    //每页条数
    private int pageSize=10;
    //总页数
    private int totalPage;
    //当前页的页码
    private int currentPage=1;
    //是否为首页
    private boolean firstPage;
    //是否为尾页
    private boolean lastPage;
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
        if(count%pageSize==0){
            totalPage= count/pageSize;
        }else{
            totalPage= count/pageSize+1;
        }

    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        if(currentPage==1){
            firstPage= true;
        }else{
            firstPage= false;
        }
        if(currentPage==totalPage){
            lastPage= true;
        }else{
            lastPage= false;
        }
    }
    public boolean isFirstPage() {
        return firstPage;
    }
    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }
    public boolean isLastPage() {
        return lastPage;
    }
    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}

