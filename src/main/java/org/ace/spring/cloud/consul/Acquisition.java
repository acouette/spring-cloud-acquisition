package org.ace.spring.cloud.consul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@EnableHystrix
public class Acquisition {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private DistributionClient distributionClient;


    @RequestMapping("/")
    public String receiveDataFromSource() {
        distributionClient.sendRealTimeData(new RealTimeData("Hello World"));
        return "sent";
    }

    @RequestMapping("/disco")
    public String disco() {
        return serviceUrl();
    }

    public String serviceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances("distribution");
        if (list != null && list.size() > 0) {
            return list.get(0).getUri().toString();
        }
        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(Acquisition.class, args);
    }

}