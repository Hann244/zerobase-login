package com.example.zerobaselogin.user.controller;

import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.model.ResponseError;
import com.example.zerobaselogin.user.model.UserInput;
import com.example.zerobaselogin.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiUserController {

    private final UserRepository userRepository;

    // 예외 처리
//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(e -> {
//                responseErrorList.add(ResponseError.of((FieldError) e)); // 에러 목록이 쌓임
//            });
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/api/user")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> {
                responseErrorList.add(ResponseError.of((FieldError) e)); // 에러 목록이 쌓임
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        try {
            User user = User.builder()
                    .email(userInput.getEmail())
                    .userName(userInput.getUserName())
                    .password(userInput.getPassword())
                    .phone(userInput.getPhone())
                    .regDate(LocalDateTime.now())
                    .build();
            userRepository.save(user);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // 예외 로그 출력
            e.printStackTrace();
            return new ResponseEntity<>("사용자 저장 중 오류 발생: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //return ResponseEntity.ok().build();
    }
}
