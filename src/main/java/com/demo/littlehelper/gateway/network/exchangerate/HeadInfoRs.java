 package com.demo.littlehelper.gateway.network.exchangerate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
  * 永豐台幣兌外幣API回傳 HeadInfo
  * @author Nancy
  * @date 2024/06/24
  */
 @Data
 public class HeadInfoRs {
     @JsonProperty("HeadText")
     private String headText;
     
     @JsonProperty("HeadAlign")
     private String headAlign;
     
     @JsonProperty("DataAlign")
     private String dataAlign;
     
     @JsonProperty("MainShow")
     private String mainShow;
     
     @JsonProperty("DetailShow")
     private String detailShow;
     
     @JsonProperty("FieldKey")
     private String fieldKey;
     
     @JsonProperty("OrderIndex")
     private String orderIndex;
     
     @JsonProperty("FieldWidth")
     private String fieldWidth;
}
