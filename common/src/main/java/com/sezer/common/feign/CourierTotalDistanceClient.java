package com.sezer.common.feign;

import com.sezer.common.dto.CourierTotalDistance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@ComponentScan
@FeignClient(value = "courier/api/v1/courier-total-distance")
public interface CourierTotalDistanceClient {

    @RequestMapping(value = "", method = RequestMethod.POST)
    void saveDistance(@RequestBody CourierTotalDistance courierTotalDistanceRequest);

    @RequestMapping(value = "/courier/{courierId}", method = RequestMethod.GET)
    CourierTotalDistance getCurrentTotalDistance(@PathVariable("courierId") Integer courierId);

}
