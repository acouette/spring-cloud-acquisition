package org.ace.spring.cloud.consul;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "distribution")
interface DistributionClient {

    @RequestMapping(method = RequestMethod.POST, value = "/real-time", consumes = "application/json")
    void sendRealTimeData(RealTimeData data);
}