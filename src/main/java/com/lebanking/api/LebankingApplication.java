package com.lebanking.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync(proxyTargetClass = true)
public class LebankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LebankingApplication.class, args);
    }

}
