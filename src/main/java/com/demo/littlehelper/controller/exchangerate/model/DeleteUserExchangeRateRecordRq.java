package com.demo.littlehelper.controller.exchangerate.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeleteUserExchangeRateRecordRq {

    @NotEmpty
    @Schema(description = "匯率紀錄id")
    private List<Long> ids;
}
