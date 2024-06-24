 package com.demo.littlehelper.controller.exchangerate.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QueryUserExchangeRateRecordRq {
    
     @NotNull
     @Schema(example = "1001", description = "使用者id")
     private Long userId;
}
