package com.sezer.worker;

import com.sezer.common.dto.LocationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("api/v1/worker-test")
public record WorkerTestController(LoggerService loggerService, WorkerService workerService) {

    @GetMapping("/total-distance/{courierId}")
    public double totalDistance(@PathVariable Integer courierId) {
        return workerService.calculateTotalDistance(courierId);
    }

    @PostMapping("/push-stream")
    public void pushStreams(@RequestBody List<LocationData> locationData) {
        workerService.sendLocations(locationData);
    }

}
