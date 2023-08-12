package com.sezer.courier.courierlog;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@Slf4j
public record CourierLogService(CourierLogRepository courierLogRepository, ObjectMapper objectMapper) {
    public void saveLog(CourierLog courierLog) {
        log.info(courierLog.toString());
        courierLogRepository.save(courierLog);
    }

    @RabbitListener(queues = {"${application.log-queue-config.name}"})
    public void receive(@Payload Message message) throws Exception {
        System.out.println("Message " + message + "  " + LocalDateTime.now());
        System.out.println(message.toString());
        String payload = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println(payload);
        CourierLog courierLog = objectMapper.readValue(payload, CourierLog.class);
        System.out.println(courierLog.toString());
        this.saveLog(courierLog);
    }
}
