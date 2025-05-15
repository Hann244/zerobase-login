package com.example.zerobaselogin.user.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.zerobaselogin.board.model.ServiceResult;
import com.example.zerobaselogin.common.model.ResponseResult;
import com.example.zerobaselogin.user.service.UserService;
import com.example.zerobaselogin.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class ApiUserInterestController {

    private final UserService userService;

    // 관심 사용자에 등록하는 API
    @PutMapping("{id}/interest")
    public ResponseEntity<?> interestUser(@PathVariable("id") Long id,
                                       @RequestHeader("F-TOKEN") String token) {
        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        ServiceResult result = userService.addInterestUser(email, id);
        return ResponseResult.result(result);

    }
}
