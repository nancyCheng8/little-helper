package com.demo.littlehelper.gateway.network.exchangerate;

import org.springframework.context.annotation.Bean;

import feign.Logger;

public class CommonClientConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
