package com.demo.littlehelper.common.websecurity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorizationCheckFilter extends OncePerRequestFilter {
    // 存放Token的Header Key
    private static final String HEADER_STRING  = "AUTHORIZATION";
    // 給定一組密鑰，用來解密以及加密使用
    private static final String LITTLE_HELPER_KY = "HeyLittleHelperIsRunningSpringSecurityAndJWT";
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        // 非登入、註冊 檢核Token
        if (!StringUtils.equalsAny(req.getServletPath(), "/user/login", "/user/register")) {
            String token = req.getHeader(HEADER_STRING );
            // 以jjwt驗證token，只要驗證成功就放行
            // 驗證失敗會拋exception，直接將錯誤訊息傳回
            if (StringUtils.isNotBlank(token)) {
                try {
                    Claims claims = Jwts.parser()
                            .setSigningKey(LITTLE_HELPER_KY)
                            .parseClaimsJws(token)
                            .getBody();
                    log.info("JWT payload: {}", claims.toString());
                    chain.doFilter(req, res);
                } catch (Exception e) {
                    log.error("Error : ", e);
                    res.setStatus(HttpStatus.FORBIDDEN.value());

                    Map<String, String> err = new HashMap<>();
                    err.put("jwt_err", e.getMessage());
                    res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(res.getOutputStream(), err);
                }
            } else {
                res.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        } else {
            chain.doFilter(req, res);
        }
    }
}
