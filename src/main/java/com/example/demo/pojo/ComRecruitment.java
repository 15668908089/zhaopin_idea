package com.example.demo.pojo;

import java.util.Date;

public class ComRecruitment {

    private Integer recruitment_id;
    private Integer com_id;
    private Integer job_id;
    private String degree;
    private String language;
    private Date langgrade;
    private String experience;
    private String address;
    private String salary;
    private String number;
    private String requirements;
    private String treatment;
    private String accumlation;
    private String description;
    private String examine="未审核";

    public Integer getRecruitment_id() {
        return recruitment_id;
    }

    public void setRecruitment_id(Integer recruitment_id) {
        this.recruitment_id = recruitment_id;
    }

    public Integer getCom_id() {
        return com_id;
    }

    public void setCom_id(Integer com_id) {
        this.com_id = com_id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getLanggrade() {
        return langgrade;
    }

    public void setLanggrade(Date langgrade) {
        this.langgrade = langgrade;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getAccumlation() {
        return accumlation;
    }

    public void setAccumlation(String accumlation) {
        this.accumlation = accumlation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }
}