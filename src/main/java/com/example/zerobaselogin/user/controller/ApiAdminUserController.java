package com.example.zerobaselogin.user.controller;

import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.model.ResponseMessage;
import com.example.zerobaselogin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiAdminUserController {

    private final UserRepository userRepository;

    // 사용자 목록과 사용자 수를 구하는 API
    @GetMapping("/api/admin/user")
    public ResponseMessage userList() {

        List<User> userList = userRepository.findAll();
        long totalUserCount = userRepository.count();

        return ResponseMessage.builder()
                .totalCount(totalUserCount)
                .data(userList)
                .build();
    }
}
