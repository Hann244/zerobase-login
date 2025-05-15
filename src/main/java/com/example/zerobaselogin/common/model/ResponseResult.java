package com.example.zerobaselogin.common.model;

import com.example.zerobaselogin.board.model.ServiceResult;
import org.springframework.http.ResponseEntity;

public class ResponseResult {
    public static ResponseEntity<?> fail(String message) {
        return ResponseEntity.badRequest().body(message);
    }

    public static ResponseEntity<?> succeess() {
        return ResponseEntity.ok().build();
    }

    public static ResponseEntity<?> result(ServiceResult result) {
        if (result.isFail()) {
            return fail(result.getMessage());
        }
        return succeess();
    }
}
