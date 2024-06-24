package com.demo.littlehelper.gateway.network.exchangerate;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "op-management", url = "https://mma.sinopac.com", configuration = CommonClientConfiguration.class)
public interface WsExchangeFeignClient {

    @PostMapping(path = "/ws/share/rate/ws_exchange.ashx")
    ResponseEntity<List<WsExchangeRs>> shareRateWsExchange();
}