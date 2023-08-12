package com.sezer.worker;

import com.sezer.common.dto.CourierTotalDistance;
import com.sezer.common.dto.LocationData;
import com.sezer.common.feign.CourierTotalDistanceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("api/v1/worker-test")
public record WorkerTestController(LoggerService loggerService, CourierTotalDistanceClient courierTotalDistanceClient,
                                   WorkerService workerService) {

    @PostMapping("/save-distance")
    public void saveDistance() {
        courierTotalDistanceClient.saveDistance(new CourierTotalDistance(null, 1, 12.03, ZonedDateTime.now()));
    }

    @PostMapping("/push-stream")
    public void pushStreams(@RequestBody List<LocationData> locationData) {
        workerService.sendLocations(locationData);
    }

}
