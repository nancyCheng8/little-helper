 package com.demo.littlehelper.common.websecurity;

import java.util.Date;

import org.springframework.context.annotation.Configuration;

import com.demo.littlehelper.model.dto.userprofile.LoginUserInfo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
 public class JwtUtil {
    // 給定一組密鑰，用來解密以及加密使用
    private static final String LITTLE_HELPER_KY = "HeyLittleHelperIsRunningSpringSecurityAndJWT";
    
     public String generateJwtToken(LoginUserInfo user) {
         // 設定30分鐘過期
         Date expireDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
         return Jwts.builder()
                 //以account當subject
                 .setSubject(user.getUserAccount())
                 .setExpiration(expireDate)
                 // MySecret是自訂的私鑰，HS512是自選的演算法，可以任意改變
                 .signWith(SignatureAlgorithm.HS512, LITTLE_HELPER_KY)
                 .compact();
     }
}
