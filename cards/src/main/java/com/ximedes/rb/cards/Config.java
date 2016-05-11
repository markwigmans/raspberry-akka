package com.ximedes.rb.cards;

import akka.actor.ActorSystem;
import akka.util.Timeout;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
class Config {

    /**
     * Default timeout for processing calls.
     */
    @Value("${actor.ask.timeout.ms:500}")
    private int timeout;

    @Value(value = "${spring.application.name}")
    private String applicationName;

    @Bean
    ActorSystem actorSystem() {
        return ActorSystem.create(applicationName);
    }

    @Bean
    MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

    @Bean
    Timeout timeout() {
        return Timeout.apply(timeout, TimeUnit.MILLISECONDS);
    }
}
