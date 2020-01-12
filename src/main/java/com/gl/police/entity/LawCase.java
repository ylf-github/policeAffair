package com.gl.police.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LawCase {
    private String cId;

    private String title;

    private String details;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date happentime;

    private String happenaddress;

    private String casetype;

    private String[] persons;

    private String orders;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getHappentime() {
        return happentime;
    }

    public void setHappentime(Date happentime) {
        this.happentime = happentime;
    }

    public String getHappenaddress() {
        return happenaddress;
    }

    public void setHappenaddress(String happenaddress) {
        this.happenaddress = happenaddress;
    }

    public String getCasetype() {
        return casetype;
    }

    public void setCasetype(String casetype) {
        this.casetype = casetype;
    }

    public String[] getPersons() {
        return persons;
    }

    public void setPersons(String[] persons) {
        this.persons = persons;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
}