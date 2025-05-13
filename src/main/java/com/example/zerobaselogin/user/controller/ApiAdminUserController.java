package com.example.zerobaselogin.user.controller;

import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.model.ResponseMessage;
import com.example.zerobaselogin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ApiAdminUserController {

    private final UserRepository userRepository;

    // 사용자 목록과 사용자 수를 구하는 API
//    @GetMapping("/api/admin/user")
//    public ResponseMessage userList() {
//
//        List<User> userList = userRepository.findAll();
//        long totalUserCount = userRepository.count();
//
//        return ResponseMessage.builder()
//                .totalCount(totalUserCount)
//                .data(userList)
//                .build();
//    }

    // 사용자 상세 조회 API
    @GetMapping("/api/admin/user/{id}")
    public ResponseEntity<?> userDetail(@PathVariable("id") Long id) {

        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            // 사용자가 없는 경우 메시지 던지기
            return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(ResponseMessage.success(user));
    }
}
