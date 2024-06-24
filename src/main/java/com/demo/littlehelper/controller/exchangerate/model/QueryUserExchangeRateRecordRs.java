 package com.demo.littlehelper.controller.exchangerate.model;

import java.util.List;

import com.demo.littlehelper.model.dto.userexchangerates.UserExchangeRates;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QueryUserExchangeRateRecordRs {

    @Schema(description = "使用者儲存的匯率資料")
    private List<UserExchangeRates> userExchangeRatesList;
}
