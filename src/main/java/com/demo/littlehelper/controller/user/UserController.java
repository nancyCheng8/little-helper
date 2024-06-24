package com.demo.littlehelper.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.littlehelper.common.websecurity.JwtUtil;
import com.demo.littlehelper.controller.user.model.UserLoginRq;
import com.demo.littlehelper.controller.user.model.UserLoginRs;
import com.demo.littlehelper.controller.user.model.UserRegisterRq;
import com.demo.littlehelper.model.dto.userprofile.LoginUserInfo;
import com.demo.littlehelper.model.dto.userprofile.UserProfile;
import com.demo.littlehelper.usecase.UserUsecase;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

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
        UserProfile userInfo = this.userLoginRqToUserProfile(userLoginRq);
        
        // 檢核、查詢會員資料 
        LoginUserInfo userProfile = userUsecase.verifyAndGetUserInfo(userInfo);
        // 建 jwtToken
        String jwtToken = jwtGenerater.generateJwtToken(userProfile);
        
        UserLoginRs rs = this.toUserLoginRs(userProfile, jwtToken);
        return ResponseEntity.ok(rs);
    }
    
    @ApiOperation("使用者註冊")
    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRq registerRq){
        UserProfile userInfo = this.userRegisterRqToUserProfile(registerRq);
        
        userUsecase.doRegister(userInfo);
        return ResponseEntity.ok().build();
    }
    
    /**
     * 將 UserLoginRq 轉為 UserProfile
     * @param userLoginRq
     * @return
     */
    private UserProfile userLoginRqToUserProfile(UserLoginRq userLoginRq) {
        return UserProfile.builder()
                .userAccount(userLoginRq.getUserAccount())
                .pwd(userLoginRq.getPwd())
                .build();
    }
    
    /**
     * 將LoginUserInfo、jwtToken 轉為 UserLoginRs
     * @param userProfile
     * @param jwtToken
     * @return
     */
    private UserLoginRs toUserLoginRs(LoginUserInfo userProfile, String jwtToken) {
        return UserLoginRs.builder()
                .userinfo(userProfile)
                .jwtToken(jwtToken)
                .build();
    }
    
    /**
     * 將 UserRegisterRq 轉為 UserProfile
     * @param registerRq
     * @return
     */
    private UserProfile userRegisterRqToUserProfile(UserRegisterRq registerRq) {
        return UserProfile.builder()
                .userAccount(registerRq.getUserAccount())
                .pwd(registerRq.getPwd())
                .userName(registerRq.getUserName())
                .email(registerRq.getEmail())
                .build();
    }
}
