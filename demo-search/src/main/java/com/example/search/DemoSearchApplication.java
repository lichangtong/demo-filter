package com.example.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient

@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
//在程序的入口ServiceHiApplication类，
// 加上@EnableHystrix注解开启断路器，这个是必须的，
// 并且需要在程序中声明断路点HystrixCommand；
// 加上@EnableHystrixDashboard注解 开启仪表盘
public class DemoSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSearchApplication.class, args);
    }
}
