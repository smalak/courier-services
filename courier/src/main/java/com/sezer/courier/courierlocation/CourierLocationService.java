package com.sezer.courier.courierlocation;

import com.sezer.courier.courier.Courier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public record CourierLocationService(CourierLocationRepository courierLocationRepository) {
    public void saveLocation(com.sezer.common.dto.CourierLocation courierLocation) {
        Courier courier = new Courier();
        courier.setId(courierLocation.getCourierId());
        CourierLocation courierL = CourierLocation.builder()
                .courier(courier)
                .location(new Point(courierLocation.getLocation().getX(), courierLocation.getLocation().getY()))
                .time(courierLocation.getTime())
                .build();

        log.info(courierLocation.toString());
        courierLocationRepository.save(courierL);
    }
}
