package com.gl.police.entity;

import java.util.Date;

public class LawCase {
    private String cId;

    private String title;

    private String details;

    private Date happentime;

    private String happenaddress;

    private String casetype;

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
}