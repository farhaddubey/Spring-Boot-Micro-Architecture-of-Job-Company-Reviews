package com.knightslife.knights.Job;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class AppConfig {
    // This is a load balancer class 
    @Bean //bean annotation tells that specialy this class needs to be managed 
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    
}
