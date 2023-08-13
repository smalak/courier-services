package com.sezer.courier.courierlog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/courier-log")
public record CourierLogController(CourierLogService courierLogService) {

    @GetMapping("/{courierId}")
    public List<CourierLog> getCourierEntrances(@PathVariable Integer courierId) {
        return courierLogService.getCourierEntrances(courierId);
    }

}
