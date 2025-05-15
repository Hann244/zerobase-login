package com.example.zerobaselogin.user.controller;

import com.example.zerobaselogin.common.exception.BizException;
import com.example.zerobaselogin.common.model.ResponseResult;
import com.example.zerobaselogin.notice.model.ResponseError;
import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.model.*;
import com.example.zerobaselogin.user.service.UserService;
import com.example.zerobaselogin.util.JWTUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiLoginController {

    private final UserService userService;

    // 회원 로그인 히스토리 기능을 구현하는 API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLogin userLogin, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseResult.fail("입력값이 정확하지 않습니다.", ResponseError.of(errors.getAllErrors()));
        }

        User user = null;
        try {
            user = userService.login(userLogin);
        } catch (BizException e) {
            return ResponseResult.fail(e.getMessage());
        }
        UserLoginToken userLoginToken = JWTUtils.createToken(user);

        if (userLoginToken == null) {
            return ResponseResult.fail("JWT 생성에 실패하였습니다.");
        }
        return ResponseResult.succeess();
    }


}
