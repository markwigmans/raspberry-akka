package com.ximedes.rb.cards;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class CardApp {

    public static void main(String[] args) {
        SpringApplication.run(CardApp.class, args);
    }
}
