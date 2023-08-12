package com.sezer.courier.courier;

import com.sezer.common.dto.Store;
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
    public void registerCustomer(@RequestBody CourierRegistrationRequest courierRegistrationRequest){
        log.info("new customer registration request {}", courierRegistrationRequest.toString());
        courierService.registerCustomer(courierRegistrationRequest);
    }

    @GetMapping
    public List<Courier> getCustomers(){
        return courierService.getCourierList();
    }

    @GetMapping("/message")
    public String sendMessage(@RequestParam String text) {
        Store store = new Store();
        store.setName(text);
        queueSender.convertAndSend(applicationProperties.getLocationQueueConfig().getExchange(), applicationProperties.getLocationQueueConfig().getRoutingKey(), store);
        return "ok. done";
    }


}
