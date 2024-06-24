package com.demo.littlehelper.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.littlehelper.common.websecurity.JwtUtil;
import com.demo.littlehelper.model.dto.userprofile.LoginUserInfo;
import com.demo.littlehelper.model.dto.userprofile.UserProfile;
import com.demo.littlehelper.usecase.UserUsecase;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "使用者登入")
@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private JwtUtil jwtGenerater;
    
    @Autowired
    private UserUsecase userUsecase;

    
    @ApiOperation("使用者登入")
    @PostMapping(path = "/login") 
    public ResponseEntity<UserLoginRs> login(@Valid @RequestBody UserLoginRq userLoginRq, HttpServletRequest httpServletRequest) {
        // userLoginRq to UserProfile
        UserProfile userInfo = UserProfile.builder()
                .userAccount(userLoginRq.getUserAccount())
                .pwd(userLoginRq.getPwd())
                .build();
        // doUserLogin、檢核、查詢會員資料 
        LoginUserInfo userProfile = userUsecase.verifyAndGetUserInfo(userInfo);
        String jwtToken = jwtGenerater.generateJwtToken(userProfile);
        
        UserLoginRs rs = UserLoginRs.builder()
                .userinfo(userProfile)
                .jwtToken(jwtToken)
                .build();
        // 回傳已登入會員資料
        return ResponseEntity.ok(rs);
    }
    
    @ApiOperation("使用者註冊")
    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRq registerRq){
        // UserRegisterRq to UserProfile
        UserProfile userInfo = UserProfile.builder()
                .userAccount(registerRq.getUserAccount())
                .pwd(registerRq.getPwd())
                .userName(registerRq.getUserName())
                .email(registerRq.getEmail())
                .build();
        
        userUsecase.doRegister(userInfo);
        return ResponseEntity.ok().build();
    }
            
}
