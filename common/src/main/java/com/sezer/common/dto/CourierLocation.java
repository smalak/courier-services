package com.sezer.common.dto;

import java.awt.*;
import java.time.ZonedDateTime;

public class CourierLocation {
    private Integer courierId;
    private Point location;
    private ZonedDateTime time;

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CourierLocation{" +
                "courierId=" + courierId +
                ", location=" + location +
                ", time=" + time +
                '}';
    }
}
