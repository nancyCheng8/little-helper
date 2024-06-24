package com.demo.littlehelper.controller.exchangerate.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveExchangeRateRq {

    @NotNull
    @Schema(example = "1001", description = "使用者id")
    private Long userId;
    
    @NotBlank
    @Schema(example = "2024/06/24 17:07:53", description = "匯率查詢日期(yyyy/MM/dd hh:MM:ss)")
    private String queryDateString;
    
    @NotBlank
    @Schema(example = "美元(USD)", description = "幣別中文描述")
    private String currencyDescription;
    
    @NotNull
    @Schema(example = "32.34800", description = "銀行買進匯率")
    private BigDecimal bankBuyRate;
    
    @NotNull
    @Schema(example = "32.45500", description = "銀行賣出匯率")
    private BigDecimal bankSellRate;
    
    @NotBlank
    @Schema(example = "USD", description = "幣別代碼")
    private String currencyCode;
    
}
