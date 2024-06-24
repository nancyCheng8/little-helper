package com.demo.littlehelper.controller.user;

import com.demo.littlehelper.model.dto.userprofile.LoginUserInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRs {
    
    @Schema(description = "使用者資訊")
    private LoginUserInfo userinfo;
    
    @Schema(description = "jwtToken")
    private String jwtToken;
}
