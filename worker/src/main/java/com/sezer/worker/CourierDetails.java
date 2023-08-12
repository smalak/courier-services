package com.sezer.worker;


import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class CourierDetails {
    private Integer courierId;
    private Double latitude;
    private Double longitude;
    private double distance; // flushed after periodically saved.
    private int counter; // save total distance after every X data arrived.
    private Map<Integer, Integer> checkStoreAfterTime = new HashMap<>();
    private ZonedDateTime lastLocationEntranceTime;

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void addDistance(double distance) {
        this.distance += distance;
    }

    public ZonedDateTime getLastLocationEntranceTime() {
        return lastLocationEntranceTime;
    }

    public void setLastLocationEntranceTime(ZonedDateTime lastLocationEntranceTime) {
        this.lastLocationEntranceTime = lastLocationEntranceTime;
    }

    public void incrementCounter() {
        counter++;
    }

    public void resetCounter() {
        counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public Map<Integer, Integer> getCheckStoreAfterTime() {
        return checkStoreAfterTime;
    }

    public void setCheckStoreAfterTime(Map<Integer, Integer> checkStoreAfterTime) {
        this.checkStoreAfterTime = checkStoreAfterTime;
    }
}
