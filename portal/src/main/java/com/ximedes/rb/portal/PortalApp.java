package com.ximedes.rb.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class PortalApp {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(PortalApp.class, args);
    }
}
