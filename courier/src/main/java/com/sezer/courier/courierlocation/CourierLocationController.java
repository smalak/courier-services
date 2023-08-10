package com.sezer.courier.courierlocation;

import com.sezer.common.dto.CourierLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/courier-location")
public record CourierLocationController(CourierLocationService courierLocationService) {

    /*
    @PostMapping
    public void create(@RequestBody CourierRegistrationRequest courierRegistrationRequest){
        log.info("new customer registration request {}", courierRegistrationRequest.toString());
        courierLocationService.registerCustomer(courierRegistrationRequest);
    }
     */

    @PostMapping
    public void create(@RequestBody CourierLocation courierLocationRequest) {
        log.info("new courier location request {}", courierLocationRequest.toString());
        courierLocationService.saveLocation(courierLocationRequest);
    }
}
