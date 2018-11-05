package com.example.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DemoUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoUploadApplication.class, args);
    }
}
