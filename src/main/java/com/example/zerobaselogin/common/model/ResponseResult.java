package com.example.zerobaselogin.common.model;

import com.example.zerobaselogin.board.entity.BoardBadReport;
import com.example.zerobaselogin.board.model.ServiceResult;
import com.example.zerobaselogin.user.model.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseResult {
    public static ResponseEntity<?> fail(String message) {

        return fail(message, null);
    }

    public static ResponseEntity<?> fail(String message, Object data) {

        return ResponseEntity.badRequest().body(ResponseMessage.fail(message, data));
    }

    public static ResponseEntity<?> succeess() {

        return succeess(null);
    }


    public static ResponseEntity<?> succeess(Object data) {
        return ResponseEntity.ok().body(data);
    }

    public static ResponseEntity<?> result(ServiceResult result) {

        if (result.isFail()) {
            return fail(result.getMessage());
        }
        return succeess();
    }
}
