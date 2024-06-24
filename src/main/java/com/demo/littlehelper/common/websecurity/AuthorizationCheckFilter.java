package com.demo.littlehelper.common.websecurity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.littlehelper.common.exceptionhandler.ErrorCode;
import com.demo.littlehelper.common.exceptionhandler.exceptions.TokenExpireException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationCheckFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;
    
    // 給定一組密鑰，用來解密以及加密使用
    private static final String LITTLE_HELPER_KY = "HeyLittleHelperIsRunningSpringSecurityAndJWT";
    // Token 開頭
    private static final String BEARER_PREFIX = "Bearer ";
    
    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse respinse, 
            @NonNull  FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        // 以下條件為沒有攜帶Token的請求
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, respinse);
            return;
        }
        
        // 非登入、註冊 檢核Token
        if (!StringUtils.equalsAny(request.getServletPath(), "/user/login", "/user/register")) {
            jwtToken = authHeader.substring(BEARER_PREFIX.length());
            // 以jjwt驗證token，只要驗證成功就放行
            // 驗證失敗會拋exception，直接將錯誤訊息傳回
            if (StringUtils.isNotBlank(jwtToken) && jwtUtil.isTokenValid(jwtToken)) {
                try {
                    Claims claims = Jwts.parser()
                            .setSigningKey(LITTLE_HELPER_KY)
                            .parseClaimsJws(jwtToken)
                            .getBody();
                    log.info("JWT payload: {}", claims.toString());
                    filterChain.doFilter(request, respinse);
                } catch (Exception e) {
                    log.error("Error : ", e);
                    respinse.setStatus(HttpStatus.FORBIDDEN.value());

                    Map<String, String> err = new HashMap<>();
                    err.put("jwt_err", e.getMessage());
                    respinse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(respinse.getOutputStream(), err);
                }
            } else {
                throw new TokenExpireException(ErrorCode.TOKEN_EXPIRED, jwtToken);
            }
        } else {
            filterChain.doFilter(request, respinse);
        }
    }
    
}
