package com.sezer.courier.courierlog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record CourierLogService(CourierLogRepository courierLogRepository) {
    public void saveLog(CourierLog courierLog) {
        log.info(courierLog.toString());
        courierLogRepository.save(courierLog);
    }
}
