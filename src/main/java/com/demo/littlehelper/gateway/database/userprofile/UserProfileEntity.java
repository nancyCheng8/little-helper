 package com.demo.littlehelper.gateway.database.userprofile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_profile")
 public class UserProfileEntity {
    
    /** 使用者ID*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private Long userId;
    
    /** 使用者帳號 */
    @Column(name = "user_account", nullable = false, unique = true)
    private String userAccount;
    
    /** 使用者密碼 */
    @Column(name = "pwd")
    private String pwd;
    
    /** 名稱 */
    @Column(name = "user_name")
    private String userName;
    
    /** email */
    @Column(name = "email")
    private String email;
}
