package com.ns.BurseXmlSystem.BurseXmlSystem;

import java.io.Serializable;
import java.util.Date;

public class Options implements Serializable {

    private String direction;
    private String sortField;
    private Date filterDate;
    private String filterTitle;

    public Options() {
    }

    public Options(String sortField, String direction, Date date, String filterTitle) {
        this.sortField = sortField;
        this.direction = direction;
        this.filterDate = date;
        this.filterTitle = filterTitle;
    }

    public Options(String direction, String sortField, Date filterDate) {
        this.direction = direction;
        this.sortField = sortField;
        this.filterDate = filterDate;
    }

    public Options(String direction, String sortField, String filterTitle) {
        this.direction = direction;
        this.sortField = sortField;
        this.filterTitle = filterTitle;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Date getFilterDate() {
        return filterDate;
    }

    public void setFilterDate(Date filterDate) {
        this.filterDate = filterDate;
    }

    public String getFilterTitle() {
        return filterTitle;
    }

    public void setFilterTitle(String filterTitle) {
        this.filterTitle = filterTitle;
    }

    @Override
    public String toString() {
        return "Options{" +
                "direction='" + direction + '\'' +
                ", sortField='" + sortField + '\'' +
                ", filterDate=" + filterDate +
                '}';
    }
}
