package com.sezer.common.dto;

public class CourierLog {

    private Integer courierId;

    private String detail;

    public CourierLog(Integer courierId, String detail) {
        this.courierId = courierId;
        this.detail = detail;
    }

    public CourierLog() {
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
