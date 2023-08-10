package com.sezer.worker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sezer.common.dto.Store;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class Processor {

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"${application.queue.name}"})
    public void receive(@Payload Message message) throws Exception {
        System.out.println("Message " + message + "  " + LocalDateTime.now());
        System.out.println(message.toString());
        String ultima = String.valueOf(message.getMessageProperties().getHeaders().get("ultima"));
        if (ultima.equals("sim")) {
            System.out.println(ultima);
        }
        String payload = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println(payload);
        Store store = objectMapper.readValue(payload, Store.class);
        System.out.println(store.toString());
        //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        if (payload.equals(1)) {
            //channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            throw new Exception("Exception occured.");
        }

    }
}
