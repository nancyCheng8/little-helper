 package com.demo.littlehelper.model.dto.userexchangerates;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserExchangeRates {
    /** 流水號 */
    private Long id;
    
    /** 使用者id */
    private Long userId;

    /** 匯率查詢日期(yyyy/MM/dd hh:MM:ss) */
    private String queryDateString;

    /** 幣別中文描述 */
    private String currencyDescription;

    /** 銀行買進匯率 */
    private BigDecimal bankBuyRate;

    /** 銀行賣出匯率 */
    private BigDecimal bankSellRate;

    /** 幣別代碼 */
    private String currencyCode;
}
