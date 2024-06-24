package com.demo.littlehelper.controller.exchangerate.model;

import java.util.List;

import com.demo.littlehelper.model.dto.exchangerate.TwdExchangeRate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryExchangeRateRs {

    @Schema(description = "查詢日期")
    String queryDateString;

    @Schema(description = "台幣兌外幣匯率")
    private List<TwdExchangeRate> twdExchangeRate;
}
