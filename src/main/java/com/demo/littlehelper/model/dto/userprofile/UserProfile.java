package com.demo.littlehelper.model.dto.userprofile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    /** 使用者ID */
    Long userId;
    /** 使用者帳號 */
    String userAccount;
    /** 使用者密碼 */
    String pwd;
    /** 名稱 */
    String userName;
    /** email */
    String email;
}
