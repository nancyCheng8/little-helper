 package com.demo.littlehelper.model.dto.userprofile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
 public class LoginUserInfo {
    /** 使用者ID */
    Long userId;
    /** 使用者帳號 */
    String userAccount;
    /** 名稱 */
    String userName;
    /** email */
    String email;
}
