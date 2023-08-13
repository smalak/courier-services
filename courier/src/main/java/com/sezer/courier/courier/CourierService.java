package com.sezer.courier.courier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @Transactional
    public void registerCourier(CourierRegistrationRequest courierRegistrationRequest) {

        Courier courier = Courier.builder()
                .id(courierRegistrationRequest.id())
                .firstName(courierRegistrationRequest.firstName())
                .lastName(courierRegistrationRequest.lastName())
                .build();

        log.info(courier.toString());
        courierRepository.save(courier);
    }

    public List<Courier> getCourierList() {
        return courierRepository.findAll();
    }

}
