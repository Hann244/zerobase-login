package com.example.zerobaselogin.common.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.zerobaselogin.common.exception.AuthFailException;
import com.example.zerobaselogin.common.model.ResponseResult;
import com.example.zerobaselogin.util.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        log.info("#######################################");
        log.info("[인터셉터] - preHandle 스타트");
        log.info("#######################################");
        log.info(request.getMethod());
        log.info(request.getRequestURI());

        if (!validJWT(request)) {
            throw new AuthFailException("인증정보가 정확하지 않습니다.");
        }

        return true;
    }

    private boolean validJWT(HttpServletRequest request) {

        String token = request.getHeader("F-TOKEN");

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        request.setAttribute("email", email);

        return true;
    }
}
