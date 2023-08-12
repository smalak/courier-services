package com.sezer.courier.courier;

import com.sezer.courier.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/courier")
public record CourierController(CourierService courierService, AmqpTemplate queueSender, ApplicationProperties applicationProperties) {

    @PostMapping
    public void registerCourier(@RequestBody CourierRegistrationRequest courierRegistrationRequest){
        log.info("new customer registration request {}", courierRegistrationRequest.toString());
        courierService.registerCourier(courierRegistrationRequest);
    }

    @GetMapping
    public List<Courier> getCouriers(){
        return courierService.getCourierList();
    }


}
