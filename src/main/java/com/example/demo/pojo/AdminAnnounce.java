package com.example.demo.pojo;

import java.util.Date;

public class AdminAnnounce {
    private Integer announce_id;

    private String announce_name;

    private String announce_description;

    private Date announce_day;

    public Integer getAnnounce_id() {
        return announce_id;
    }

    public void setAnnounce_id(Integer announce_id) {
        this.announce_id = announce_id;
    }

    public String getAnnounce_name() {
        return announce_name;
    }

    public void setAnnounce_name(String announce_name) {
        this.announce_name = announce_name;
    }

    public String getAnnounce_description() {
        return announce_description;
    }

    public void setAnnounce_description(String announce_description) {
        this.announce_description = announce_description;
    }

    public Date getAnnounce_day() {
        return announce_day;
    }

    public void setAnnounce_day(Date announce_day) {
        this.announce_day = announce_day;
    }
}