package com.demo.littlehelper.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.littlehelper.gateway.database.userprofile.UserProfileResource;
import com.demo.littlehelper.model.dto.userprofile.LoginUserInfo;
import com.demo.littlehelper.model.dto.userprofile.UserProfile;

@Service
public class UserUsecase {
     @Autowired
     private UserProfileResource userProfileResource;
     
    /**
     * 檢核並回傳登入者資訊
     * 
     * @param userInfo 
     * @return UserProfile
     */
    public LoginUserInfo verifyAndGetUserInfo(UserProfile userInfo) {
        // 檢核登入次數
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
}
