package com.demo.littlehelper.model.dto.exchangerate;

import java.util.List;

import lombok.Data;

@Data
public class WsExchangeRate {
    /** 警語 */
    String titleInfo;
	
    /** 查詢日期 */
    String queryDateString;
	
    /** 台幣兌外幣匯率 */
    private List<TwdExchangeRate> twdExchangeRate;
}
