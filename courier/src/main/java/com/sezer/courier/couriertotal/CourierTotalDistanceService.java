package com.sezer.courier.couriertotal;

import com.sezer.courier.courier.Courier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CourierTotalDistanceService {

    private final CourierTotalDistanceRepository courierTotalDistanceRepository;

    public CourierTotalDistanceService(CourierTotalDistanceRepository courierTotalDistanceRepository) {
        this.courierTotalDistanceRepository = courierTotalDistanceRepository;
    }

    @Transactional
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
