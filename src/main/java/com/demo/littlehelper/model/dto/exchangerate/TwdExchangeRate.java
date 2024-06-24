package com.demo.littlehelper.model.dto.exchangerate;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

/**
 * 台幣兌外幣匯率
 * @author Nancy
 * @date 2024/06/24
 */
@Data
@Builder
public class TwdExchangeRate {
    /** 幣別中文描述: */
    private String currencyDescription;
    
    /** 國旗圖樣 */
    private String flagImage;
    
    /** 銀行買進匯率 */
    private BigDecimal bankBuyRate;
    
    /** 銀行賣出匯率 */
    private BigDecimal bankSellRate;
    
    /** 幣別代碼 */
    private String currencyCode;

}
