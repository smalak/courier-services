package com.sezer.common.feign;

import com.sezer.common.dto.LocationData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ComponentScan
@FeignClient(value = "courier/api/v1/courier-location")
public interface CourierLocationClient {

    @RequestMapping(method = RequestMethod.POST)
    void saveLocation(@RequestBody LocationData locationDataRequest);

}
