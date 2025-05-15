package com.example.zerobaselogin.common.model;

import com.example.zerobaselogin.board.model.ServiceResult;
import com.example.zerobaselogin.user.model.ResponseMessage;
import org.springframework.http.ResponseEntity;

public class ResponseResult {
    public static ResponseEntity<?> fail(String message) {

        return ResponseEntity.badRequest().body(ResponseMessage.fail(message));
    }

    public static ResponseEntity<?> succeess() {

        return ResponseEntity.ok().body(ResponseMessage.success());
    }

    public static ResponseEntity<?> result(ServiceResult result) {
        if (result.isFail()) {
            return fail(result.getMessage());
        }
        return succeess();
    }
}
