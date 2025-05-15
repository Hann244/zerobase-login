package com.example.zerobaselogin.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.model.UserLoginToken;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Date;

@UtilityClass
public class JWTUtils {

    // KEY 상수로 설정
    private static final String KEY = "fastcampus";
    private static final String CLAIN_USER_ID = "user_id";

    // 토큰 생성
    public static UserLoginToken createToken(User user) {

        if (user == null) {
            return null;
        }

        // 유효기간 한 달로 설정
        LocalDateTime expiredDateTime = LocalDateTime.now().plusMonths(1);
        Date expiredDate = java.sql.Timestamp.valueOf(expiredDateTime);

        // 토큰 발행 시점(jwt 라이브러리 필요)
        String token = JWT.create()
                .withExpiresAt(expiredDate) // 유효기간
                .withClaim(CLAIN_USER_ID, user.getId()) // 실질적으로 키와 값들을 저장
                .withSubject(user.getUserName()) // 일반적으로 사용자 이름을 넣음
                .withIssuer(user.getEmail())
                .sign(Algorithm.HMAC512(KEY.getBytes())); // 암호화 키를 바이트로

        return UserLoginToken.builder()
                .token(token)
                .build();
    }

    // email을 가지고 오기 위해 Issuer를 찾는 메서드
    public static String getIssuer(String token) {
        
        String issuer = JWT.require(Algorithm.HMAC512(KEY.getBytes()))
                .build()
                .verify(token)
                .getIssuer();

        return issuer;
    }
}
