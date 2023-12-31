package com.sezer.worker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sezer.common.dto.CourierTotalDistance;
import com.sezer.common.dto.LocationData;
import com.sezer.common.dto.Store;
import com.sezer.common.feign.CourierLocationClient;
import com.sezer.common.feign.CourierTotalDistanceClient;
import com.sezer.common.util.DateUtil;
import com.sezer.common.util.DistanceCalculatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class Processor {

    @Value("${application.max-possible-speed}")
    Double maximumSpeed;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CourierLocationClient courierLocationClient;

    @Autowired
    private CourierTotalDistanceClient courierTotalDistanceClient;
    private final LoggerService loggerService;

    //Defined for caching last location data and holding some calculations.
    private Map<Integer, CourierDetails> courierDetailsCache = new HashMap<>();
    private Map<Integer, Store> storeMap = new HashMap<>();

    public Processor(LoggerService loggerService, PreperationService preperationService) {
        this.loggerService = loggerService;
        initializeStoreMap(preperationService.getStores());
    }

    private void initializeStoreMap(List<Store> stores) {
        for (int i = 0; i < stores.size(); i++) {
            // Set key identity to store to ease filtering later operations.
            storeMap.put(i, stores.get(i));
        }
    }

    @RabbitListener(queues = {"${application.location-queue-config.name}"})
    public void receive(@Payload Message message) throws Exception {
        // Receive location
        String payload = new String(message.getBody(), StandardCharsets.UTF_8);
        LocationData locationData = objectMapper.readValue(payload, LocationData.class);

        Integer courierId = locationData.getCourier();

        // Store last location in memory
        CourierDetails courierDetails = courierDetailsCache.get(courierId);
        if (courierDetails == null) {
            courierDetails = new CourierDetails();
            courierDetails.setCourierId(courierId);
            courierDetails.setLatitude(locationData.getLat());
            courierDetails.setLongitude(locationData.getLng());
            courierDetailsCache.put(courierId, courierDetails);
        }

        // Save location
        courierLocationClient.saveLocation(locationData);

        // Calculate distance between two location and add store in local memory first
        double haversineDistance = DistanceCalculatorUtil.haversineDistance(courierDetails.getLatitude(), courierDetails.getLongitude(), locationData.getLat(), locationData.getLng());
        courierDetails.addDistance(haversineDistance);
        courierDetails.incrementCounter();

        // Calculate and save every after 10 location data arrived.
        if (courierDetails.getCounter() == 10) {
            saveDistance(courierDetails, locationData);
        }

        checkMigrosLocations(courierDetails, locationData);

    }

    void saveDistance(CourierDetails courierDetails, LocationData locationData) {
        // save distance
        // Calculate and save every after 10 data arrived.
        log.info("counter {}", courierDetails.getCounter());
        courierDetails.resetCounter();
        CourierTotalDistance currentTotalDistance = courierTotalDistanceClient.getCurrentTotalDistance(courierDetails.getCourierId());
        double total = courierDetails.getDistance();
        Integer id = null;
        if (currentTotalDistance != null) {
            id = currentTotalDistance.getId();
            Double distance = currentTotalDistance.getDistance();
            total += distance;
        }

        courierTotalDistanceClient.saveDistance(new CourierTotalDistance(id, courierDetails.getCourierId(), total, DateUtil.epochToZonedDateTime(locationData.getTime())));
        courierDetails.resetDistance();

    }

    void checkMigrosLocations(CourierDetails courierDetails, LocationData locationData) {
        // Check Migros locations

        // This means some of arrived location is too far from any store, no need to do calculations for them.
        Map<Integer, Integer> checkStoreAfterTime = courierDetails.getCheckStoreAfterTime();

        for (Integer key : storeMap.keySet()) {
            Store store = storeMap.get(key);
            Integer controlAfter = checkStoreAfterTime.get(key);
            if (controlAfter != null && controlAfter.compareTo(locationData.getTime()) > 0)
                continue;
            Double latitude = store.getLat();
            Double longitude = store.getLng();
            double distancetoStore = DistanceCalculatorUtil.haversineDistance(latitude, longitude, locationData.getLat(), locationData.getLng());
            log.info("Distance to store is {} - {}", store.getName(), distancetoStore);

            // If last checked location is away 5KM do not check for a while
            // I assumed courier cannot go faster specified maximum speed (200 KM/H)
            if (distancetoStore > 5) {
                // Do not check for a while for that store
                int checkAfterSeconds = (int) (distancetoStore / maximumSpeed * 3600);
                controlAfter = locationData.getTime() + checkAfterSeconds;
                System.out.println(store.getName() + " Check after seconds " + checkAfterSeconds);
                System.out.println(controlAfter);
                checkStoreAfterTime.put(key, controlAfter);
            } else {
                if (distancetoStore < 0.1) {
                    // Write log to queue
                    log.info("push log to queue");
                    loggerService.log(courierDetails.getCourierId(), store, locationData);
                    // Do not check in 1 minute.
                    controlAfter = locationData.getTime() + 60;
                    System.out.println("Control after because of entrance " + controlAfter);
                    checkStoreAfterTime.put(key, controlAfter);
                }
            }

        }
    }

    public Map<Integer, CourierDetails> getCourierDetailsCache() {
        return courierDetailsCache;
    }
}
