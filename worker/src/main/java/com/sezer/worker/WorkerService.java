package com.sezer.worker;

import com.sezer.common.dto.CourierTotalDistance;
import com.sezer.common.dto.LocationData;
import com.sezer.common.feign.CourierTotalDistanceClient;
import com.sezer.worker.rabbitmq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private Processor processor;
    @Autowired
    private CourierTotalDistanceClient courierTotalDistanceClient;

    public void sendLocations(List<LocationData> locationData) {
        locationData.stream().forEach(d -> messageSender.sendLocation(d));
    }

    public double calculateTotalDistance(Integer courierId) {
        CourierTotalDistance currentTotalDistance = courierTotalDistanceClient.getCurrentTotalDistance(courierId);
        CourierDetails courierDetails = processor.getCourierDetailsCache().get(courierId);
        double inMemo = 0.0, db = 0.0;
        if(courierDetails != null)
            inMemo = courierDetails.getDistance();
        if(currentTotalDistance != null)
            db = currentTotalDistance.getDistance();
        return inMemo + db;
    }
}
