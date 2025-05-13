package com.example.zerobaselogin.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JWTUtils {

    // KEY 상수로 설정
    private static final String KEY = "fastcampus";

    // email을 가지고 오기 위해 Issuer를 찾는 메서드
    public static String getIssuer(String token) {
        
        String issuer = JWT.require(Algorithm.HMAC512(KEY.getBytes()))
                .build()
                .verify(token)
                .getIssuer();

        return issuer;
    }
}
