package com.ximedes.rb.issuance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IssuanceApp {

    public static void main(String[] args) {
        SpringApplication.run(IssuanceApp.class, args);
    }
}
