package com.sezer.worker;

import com.sezer.common.dto.CourierLog;
import com.sezer.common.dto.LocationData;
import com.sezer.common.dto.Store;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    @Value("${application.log-queue-config.exchange}")
    String logExchange;

    @Value("${application.log-queue-config.routing-key}")
    String routingKey;

    private final AmqpTemplate queueSender;

    public LoggerService(AmqpTemplate queueSender) {
        this.queueSender = queueSender;
    }

    public String log(Integer courier, Store store, LocationData locationData) {
        String message = String.format("Courier has arrived %s in lat %.8f and lng %.8f in time %d", store.getName(), locationData.getLat(), locationData.getLng(), locationData.getTime());
        CourierLog courierLog = new CourierLog(courier, message);
        queueSender.convertAndSend(logExchange, routingKey, courierLog);
        return "ok. done";
    }
}
