package com.demo.littlehelper.common.websecurity;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class JwtBlackListService {
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**黑名單前綴 */
    private static final String BLACKLIST_PREFIX = "jwt:blacklist:"; 
    
    /**
     * 將jwt加入黑名單
     * @param jwt
     */
    public void addJwtToBlackList(String jwt) {
        String key = BLACKLIST_PREFIX + jwt;
        // 設置存活時間
        Duration expirationDuration  = Duration.ofMinutes(30);
        // 將jwt加入黑名單並設置存活時間，當過了存活時間就會自動被Redis自動清除
        redisTemplate.opsForValue().set(key, "im here", expirationDuration);
    }

    /**
     * 檢查jwt是否在黑名單中
     * @param jwt
     * @return boolean
     */
    public boolean isJwtInBlackList(String jwt) {
        String key = BLACKLIST_PREFIX + jwt;
        return redisTemplate.opsForValue().get(key) != null;
    }
}
