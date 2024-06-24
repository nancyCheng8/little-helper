package com.demo.littlehelper.controller.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 使用者註冊資訊
 */
@Data
public class UserLoginRq {
    
    @NotBlank
    @Size(max = 10)
    @Schema(example = "user01", description = "使用者帳號")
    String userAccount;
    
    @NotBlank
    @Schema(example = "sdfghjkytrews", description = "使用者密碼")
    String pwd;
}
