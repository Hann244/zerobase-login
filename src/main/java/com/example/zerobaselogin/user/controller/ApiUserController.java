package com.example.zerobaselogin.user.controller;

import com.example.zerobaselogin.notice.entity.Notice;
import com.example.zerobaselogin.notice.model.NoticeResponse;
import com.example.zerobaselogin.notice.repository.NoticeRepository;
import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.exception.ExistsEmailException;
import com.example.zerobaselogin.user.exception.PasswordNotMatchException;
import com.example.zerobaselogin.user.exception.UserNotFoundException;
import com.example.zerobaselogin.notice.model.ResponseError;
import com.example.zerobaselogin.user.model.*;
import com.example.zerobaselogin.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiUserController {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;

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

    // 단순 사용자 등록
    /*
    @PostMapping("/api/user")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> {
                responseErrorList.add(ResponseError.of((FieldError) e)); // 에러 목록이 쌓임
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .email(userInput.getEmail())
                .userName(userInput.getUserName())
                .password(userInput.getPassword())
                .phone(userInput.getPhone())
                .regDate(LocalDateTime.now())
                .build();
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
     */

    // 회원정보 수정
    @PutMapping("/api/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,
                                                      @RequestBody @Valid Userupdate userupdate,
                                                      Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> {
                responseErrorList.add(ResponseError.of((FieldError) e)); // 에러 목록이 쌓임
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        user.setPhone(userupdate.getPhone());
        user.setUpdateDate(LocalDateTime.now());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    // 사용자 정보 예외 처리
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> UserNotFoundExceptionHandler(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/user/{id}")
    public UserResponse getUser(@PathVariable("id") Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        //UserResponse userResponse = new UserResponse(user);
        UserResponse userResponse = UserResponse.of(user);

        return userResponse;
    }

    @GetMapping("/api/user/{id}/notice")
    public List<NoticeResponse> userNotice(@PathVariable("id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        List<Notice> noticeList = noticeRepository.findByUser(user);

        List<NoticeResponse> noticeResponseList = new ArrayList<>();
        noticeList.stream().forEach((e) -> {
            noticeResponseList.add(NoticeResponse.of(e));
        });

        return noticeResponseList;
    }

    // 사용자 등록 시 존재하는 이메일 예외처리
    /*
    @PostMapping("/api/user")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> {
                responseErrorList.add(ResponseError.of((FieldError) e));
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.countByEmail(userInput.getEmail()) > 0) {
            throw new ExistsEmailException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(userInput.getEmail())
                .userName(userInput.getUserName())
                .phone(userInput.getPhone())
                .password(userInput.getPassword())
                .regDate(LocalDateTime.now())
                .build();
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

     */

    // 이메일 존재 여부 예외 처리 & 비밀번호 확인
    @ExceptionHandler(value = {ExistsEmailException.class, PasswordNotMatchException.class})
    public ResponseEntity<?> ExistsEmailExceptionHandler(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 비밀번호 수정 API
    @PatchMapping("/api/user/{id}/password")
    public ResponseEntity<?> updateUserPassword(@PathVariable("id") Long id, @RequestBody UserInputPassword userInputPassword, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> {
                responseErrorList.add(ResponseError.of((FieldError) e));
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByIdAndPassword(id, userInputPassword.getPassword())
                .orElseThrow(() -> new PasswordNotMatchException("비밀번호가 일치하지 않습니다."));

        user.setPassword(userInputPassword.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    // 비밀번호 암호화 메서드
    private String getEncryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);

    }

    // 비밀번호 암호화
    @PostMapping("/api/user")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> {
                responseErrorList.add(ResponseError.of((FieldError) e));
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.countByEmail(userInput.getEmail()) > 0) {
            throw new ExistsEmailException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = getEncryptPassword(userInput.getPassword());

        User user = User.builder()
                .email(userInput.getEmail())
                .userName(userInput.getUserName())
                .phone(userInput.getPhone())
                .password(encodedPassword)
                .regDate(LocalDateTime.now())
                .build();
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    // 회원탈퇴
    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        try {
            userRepository.delete(user);
        } catch (DataIntegrityViolationException e) {
            String message = "제약조건에 문제가 발생하였습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            String message = "회원 탈퇴 중 문제가 발생하였습니다.";
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok().build();

    }

    // 아이디 찾기
    @GetMapping("/api/user")
    public ResponseEntity<?> findUser(@RequestBody UserInputFind userInputFind) {

        User user = userRepository.findByUserNameAndPhone(userInputFind.getUserName(), userInputFind.getPhone())
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        UserResponse userResponse = UserResponse.of(user);

        return ResponseEntity.ok().body(userResponse);
    }
}
