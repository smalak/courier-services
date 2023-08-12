package com.sezer.courier.couriertotal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/courier-total-distance")
public record CourierTotalDistanceController(CourierTotalDistanceService courierTotalDistanceService) {

    /*
    @PostMapping
    public void create(@RequestBody CourierRegistrationRequest courierRegistrationRequest){
        log.info("new customer registration request {}", courierRegistrationRequest.toString());
        courierLocationService.registerCustomer(courierRegistrationRequest);
    }
     */

    @PostMapping
    public void saveDistance(@RequestBody com.sezer.common.dto.CourierTotalDistance courierTotalDistanceRequest) {
        log.info("new courier location request {}", courierTotalDistanceRequest.toString());
        courierTotalDistanceService.saveTotalDistance(courierTotalDistanceRequest);
    }

    @GetMapping("/courier/{courierId}")
    public CourierTotalDistance getCurrentTotalDistance(@PathVariable Integer courierId) {
        log.info("request to get total distance of courier {}",courierId);
        return courierTotalDistanceService.getCourierTotalDistance(courierId);
    }
}
