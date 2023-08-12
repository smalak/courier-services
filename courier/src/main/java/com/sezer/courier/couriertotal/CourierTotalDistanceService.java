package com.sezer.courier.couriertotal;

import com.sezer.courier.courier.Courier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record CourierTotalDistanceService(CourierTotalDistanceRepository courierTotalDistanceRepository) {
    public void saveTotalDistance(com.sezer.common.dto.CourierTotalDistance courierTotalDistance) {
        log.info(courierTotalDistance.toString());
        CourierTotalDistance totalDistance = CourierTotalDistance.builder()
                .id(courierTotalDistance.getId())
                .distance(courierTotalDistance.getDistance())
                .time(courierTotalDistance.getTime())
                .courier(new Courier(courierTotalDistance.getCourierId())).build();
        courierTotalDistanceRepository.save(totalDistance);
    }

    public CourierTotalDistance getCourierTotalDistance(Integer courierId) {
        CourierTotalDistance courierTotalDistance = courierTotalDistanceRepository.findByCourierId(courierId).orElse(null);
        return courierTotalDistance;
    }
}
