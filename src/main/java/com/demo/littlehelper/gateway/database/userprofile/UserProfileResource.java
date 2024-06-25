package com.demo.littlehelper.gateway.database.userprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.littlehelper.common.exceptionhandler.ErrorCode;
import com.demo.littlehelper.common.exceptionhandler.exceptions.UserNotFoundException;
import com.demo.littlehelper.model.dto.userprofile.UserProfile;

import jakarta.transaction.Transactional;

@Component
public class UserProfileResource {

    @Autowired
    private UserProfileRepository userProfileRepository;

    /**
     * 以帳號密碼查詢使用者資料
     * 
     * @param userAccount
     * @param pwd
     * @return UserProfile
     */
    public UserProfile findByUserAccountAndPwd(String userAccount, String pwd) {
        return userProfileRepository.findByUserAccountAndPwd(userAccount, pwd)
                .map(this::convertToUserProfile)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
    }
    
    /**
     * 儲存會員資訊
     * 
     * @param userInfo
     */
    @Transactional
    public void saveUserInfo(UserProfile userInfo) {
        userProfileRepository.save(this.convertToEntity(userInfo));
    }
    
    /**
     * 將 UserProfileEntity 轉為 UserProfile
     * @param userInfo 
     * @return UserProfileEntity
     */
    private UserProfileEntity convertToEntity(UserProfile userInfo) {
        return UserProfileEntity.builder()
                .userId(userInfo.getUserId())
                .pwd(userInfo.getPwd())
                .userAccount(userInfo.getUserAccount())
                .userName(userInfo.getUserName())
                .email(userInfo.getEmail())
                .build();
    }
    
    /**
     * 將 UserProfileEntity 轉為 UserProfile
     * @param entity
     * @return UserProfile
     */
    private UserProfile convertToUserProfile(UserProfileEntity entity) {
        return UserProfile.builder()
                .userId(entity.getUserId())
                .userAccount(entity.getUserAccount())
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .build();
    }
}
