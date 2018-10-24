package com.example.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
public class DemoSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSearchApplication.class, args);
    }
}
