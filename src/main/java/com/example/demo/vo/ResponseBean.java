package com.example.demo.vo;

import java.util.List;

import com.example.demo.vo.*;

public class ResponseBean<T> {
    // 返回码
    private String code;
    // 提示信息
    private String msg;
    // 携带的数据
    private List<T> list;

    private PageBean page;

    public PageBean getPage() {
        return page;
    }
    public void setPage(PageBean page) {
        this.page = page;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }


}
