package com.example.demo.pojo;

import java.util.Date;

public class AdminNews {
    private Integer news_id;

    private String news_name;

    private String news_description;

    private Date news_day;

    private Integer news_click=0;

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    public String getNews_name() {
        return news_name;
    }

    public void setNews_name(String news_name) {
        this.news_name = news_name;
    }

    public String getNews_description() {
        return news_description;
    }

    public void setNews_description(String news_description) {
        this.news_description = news_description;
    }

    public Date getNews_day() {
        return news_day;
    }

    public void setNews_day(Date news_day) {
        this.news_day = news_day;
    }

    public Integer getNews_click() {
        return news_click;
    }

    public void setNews_click(Integer news_click) {
        this.news_click = news_click;
    }
}