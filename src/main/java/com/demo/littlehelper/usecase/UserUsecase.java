package com.demo.littlehelper.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.demo.littlehelper.common.websecurity.JwtBlackListService;
import com.demo.littlehelper.gateway.database.userprofile.UserProfileResource;
import com.demo.littlehelper.model.dto.userprofile.LoginUserInfo;
import com.demo.littlehelper.model.dto.userprofile.UserProfile;

@Service
public class UserUsecase {
     @Autowired
     private UserProfileResource userProfileResource;
     @Autowired
     private JwtBlackListService jwtBlackListService;
     
    /**
     * 檢核並回傳登入者資訊
     * 
     * @param userInfo 
     * @return UserProfile
     */
    public LoginUserInfo verifyAndGetUserInfo(UserProfile userInfo) {
        // 檢核帳號密碼

        // 檢核通過
        UserProfile loginUserInfo = userProfileResource
                .findByUserAccountAndPwd(userInfo.getUserAccount(), userInfo.getPwd());
        
        // DB userInfo 轉 LoginUserInfo
        // 回傳使用者資訊
        return LoginUserInfo.builder()
                .userId(loginUserInfo.getUserId())
                .userAccount(loginUserInfo.getUserAccount())
                .userName(loginUserInfo.getUserName())
                .email(loginUserInfo.getEmail())
                .build();
    }
    
    /**
     * 註冊使用者資料
     * 
     * @param userInfo
     */
    public void doRegister(UserProfile userInfo) {
        userProfileResource.saveUserInfo(userInfo);
    }
    
    /**
     * 使用者登出
     * @param token
     */
    public void logout(String token) {
        // 調用JWT黑名單服務將該token加入到黑名單中      
        jwtBlackListService.addJwtToBlackList(token.substring(7));
        // 清除Spring Security上下文
        SecurityContextHolder.clearContext();
    }
}
