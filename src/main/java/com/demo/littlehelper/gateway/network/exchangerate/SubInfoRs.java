 package com.demo.littlehelper.gateway.network.exchangerate;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 永豐台幣兌外幣API回傳 SubInfo
 * @author Nancy
 * @date 2024/06/24
 */
@Data
 public class SubInfoRs {
    @JsonProperty("DataValue1")
    private String dataValue1;
    
    @JsonProperty("DataValue1Img")
    private String dataValue1Img;
    
    @JsonProperty("DataValue2")
    private BigDecimal dataValue2;
    
    @JsonProperty("DataValue3")
    private BigDecimal dataValue3;
    
    @JsonProperty("DataValue4")
    private String dataValue4;
}
