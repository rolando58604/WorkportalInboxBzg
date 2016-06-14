package com.rolandoamarillo.workportalinboxbizagi.models;

import java.util.Date;

public class Task {

    private String employee;
    private Date beginDate;
    private Date endDate;
    private TaskState state = TaskState.NOT_REVIEWED;

    public String getEmployee() {
        return employee;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public long getNumberOfDays() {
        return calculateDays(beginDate, endDate);
    }

    public long calculateDays(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
    }

    public enum TaskState {
        NOT_REVIEWED, ACCEPTED, REJECTED;
    }
}
