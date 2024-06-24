package com.demo.littlehelper.gateway.network.exchangerate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.demo.littlehelper.common.exceptionhandler.ErrorCode;
import com.demo.littlehelper.common.exceptionhandler.exceptions.DataNotFoundException;
import com.demo.littlehelper.common.utils.JsonUtil;
import com.demo.littlehelper.model.dto.exchangerate.TwdExchangeRate;
import com.demo.littlehelper.model.dto.exchangerate.WsExchangeRate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WsExchangeGateway {

    @Autowired
    private WsExchangeFeignClient wsExchangeFeignClient;

    /**
     * 查詢台幣兌外幣匯率
     * @return WsExchangeRate
     */
    public WsExchangeRate queryTWDExchangeRate() {
        ResponseEntity<List<WsExchangeRs>> responseEntity = wsExchangeFeignClient.shareRateWsExchange();
        log.info("###永豐台幣兌外幣匯率: {}", JsonUtil.getJsonString(responseEntity));
        
        WsExchangeRs response = Optional.ofNullable(responseEntity.getBody().get(0))
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.WS_EXCHANGE_FEIGNCLIENT_DATA_NOT_FOUND));
        return this.wsExchangeRsToWsExchangeRate(response);
    }
    
    /**
     * 將 WsExchangeRs 轉為 WsExchangeRate
     * @param response
     * @return
     */
    private WsExchangeRate wsExchangeRsToWsExchangeRate(WsExchangeRs response) {
        WsExchangeRate exchangeRateResult = new WsExchangeRate();
        exchangeRateResult.setTitleInfo(response.getTitleInfo());
        exchangeRateResult.setQueryDateString(response.getQueryDate());
        List<TwdExchangeRate> twdExchangeRateList = response.getSubInfo().stream()
                .map(this::subInfoToTwdExchangeRate)
                .toList();
        
        exchangeRateResult.setTwdExchangeRate(twdExchangeRateList);
        return exchangeRateResult;
    }
    
    /**
     * 將 SubInfoRs 轉為 TwdExchangeRate
     * @param subInfo
     * @return
     */
    private TwdExchangeRate subInfoToTwdExchangeRate(SubInfoRs subInfo){
        return TwdExchangeRate.builder()
                .currencyDescription(subInfo.getDataValue1())
                .flagImage(subInfo.getDataValue1Img())
                .bankBuyRate(subInfo.getDataValue2())
                .bankSellRate(subInfo.getDataValue3())
                .currencyCode(subInfo.getDataValue4())
                .build();
    }
}
