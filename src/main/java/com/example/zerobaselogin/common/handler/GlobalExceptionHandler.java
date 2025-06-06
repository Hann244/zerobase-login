package com.example.zerobaselogin.common.handler;

import com.example.zerobaselogin.common.exception.AuthFailException;
import com.example.zerobaselogin.common.model.ResponseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthFailException.class)
    public ResponseEntity<?> AuthFailException(AuthFailException exception) {
        return ResponseResult.fail("[인증실패]" + exception.getMessage());
    }
}
