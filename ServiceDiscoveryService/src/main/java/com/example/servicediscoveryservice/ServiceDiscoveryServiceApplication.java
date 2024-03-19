package com.example.servicediscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryServiceApplication.class, args);
    }

}
