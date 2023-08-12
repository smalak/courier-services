package com.sezer.worker;

import com.sezer.common.dto.LocationData;
import com.sezer.worker.rabbitmq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private MessageSender messageSender;

    public void sendLocations(List<LocationData> locationData) {
        locationData.stream().forEach(d -> messageSender.sendLocation(d));
    }
}
