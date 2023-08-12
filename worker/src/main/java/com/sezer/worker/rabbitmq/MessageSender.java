package com.sezer.worker.rabbitmq;

import com.sezer.common.dto.LocationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageSender {

    @Value("${application.location-queue-config.exchange}")
    String exchange;

    @Value("${application.location-queue-config.routing-key}")
    String routingKey;

    private final AmqpTemplate queueSender;

    public MessageSender(AmqpTemplate queueSender) {
        this.queueSender = queueSender;
    }

    public void sendLocation(LocationData locationData) {
        queueSender.convertAndSend(exchange, routingKey, locationData);
        log.info("Location data has been sent to queue {}", locationData);
    }
}
