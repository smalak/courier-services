package com.sezer.courier.courierlocation;

import com.sezer.common.dto.LocationData;
import com.sezer.courier.courier.Courier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
@Slf4j
public record CourierLocationService(CourierLocationRepository courierLocationRepository) {
    public void saveLocation(LocationData locationData) {
        Instant instant = Instant.ofEpochSecond(locationData.getTime());
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
        CourierLocation courierL = CourierLocation.builder()
                .courier(new Courier(locationData.getCourier()))
                .location(new Point(locationData.getLat(), locationData.getLng()))
                .time(zonedDateTime)
                .build();

        log.info(locationData.toString());
        courierLocationRepository.save(courierL);
    }
}
