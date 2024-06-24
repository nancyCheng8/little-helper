package com.demo.littlehelper.gateway.network.exchangerate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WsExchangeRs {
    
    /** 警語 */
    @JsonProperty("TitleInfo")
    private String titleInfo;

    /** 查詢時間(yyyy/MM/dd hh:MM:ss) */
    @JsonProperty("QueryDate")
    private String queryDate;

    /** SubInfo 欄位說明 */
    @JsonProperty("HeadInfo")
    private List<HeadInfoRs> headInfoR;

    /** 台幣兌外幣匯率 */
    @JsonProperty("SubInfo")
    private List<SubInfoRs> subInfo;

    /** 是否成功 */
    @JsonProperty("Header")
    private String header;
}
