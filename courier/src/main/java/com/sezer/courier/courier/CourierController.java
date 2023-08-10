package com.sezer.courier.courier;

import com.sezer.common.dto.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/courier")
public record CourierController(CourierService courierService, AmqpTemplate queueSender) {

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
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("ultima", "sim");
        Message message = new Message(text.getBytes(), messageProperties);

        queueSender.convertAndSend("direct-exchange", "routing-key", store);
        return "ok. done";
    }


}
