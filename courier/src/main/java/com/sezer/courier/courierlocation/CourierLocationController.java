package com.sezer.courier.courierlocation;

import com.sezer.common.dto.LocationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public void saveLocation(@RequestBody LocationData locationDataRequest) {
        log.info("new courier location request {}", locationDataRequest.toString());
        courierLocationService.saveLocation(locationDataRequest);
    }
}
