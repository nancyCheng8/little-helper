package com.demo.littlehelper.controller.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 使用者註冊資訊
 */
@Data
public class UserRegisterRq {
    
    @NotBlank
    @Size(max = 10)
    @Schema(example = "user01", description = "使用者帳號")
    String userAccount;
    
    @NotBlank
    @Schema(example = "sdfghjkytrews", description = "使用者密碼")
    String pwd;
    
    @NotBlank
    @Size(max = 10)
    @Schema(example = "王曉明", description = "使用者名稱")
    String userName;
    
    @NotBlank
    @Size(max = 100)
    @Pattern(regexp = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$")
    @Schema(example = "1123hey@gmail.com" , description = "email信箱")
    String email;
}
