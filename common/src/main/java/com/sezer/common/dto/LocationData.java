package com.sezer.common.dto;

public class LocationData {
    private Integer courier;
    private Double lat;
    private Double lng;
    private Integer time;

    public Integer getCourier() {
        return courier;
    }

    public void setCourier(Integer courier) {
        this.courier = courier;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "courier=" + courier +
                ", lat=" + lat +
                ", lng=" + lng +
                ", time=" + time +
                '}';
    }
}
