package com.sezer.common.dto;

import java.time.ZonedDateTime;

public class CourierTotalDistance {

    private Integer id;
    private Integer courierId;
    private Double distance;
    private ZonedDateTime time;

    public CourierTotalDistance(Integer id, Integer courierId, Double distance, ZonedDateTime time) {
        this.id = id;
        this.courierId = courierId;
        this.distance = distance;
        this.time = time;
    }

    public CourierTotalDistance() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CourierTotalDistance{" +
                "courierId=" + courierId +
                ", distance=" + distance +
                ", time=" + time +
                '}';
    }
}
