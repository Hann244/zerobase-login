package com.example.zerobaselogin.user.controller;

import com.example.zerobaselogin.notice.repository.NoticeRepository;
import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.entity.UserLoginHistory;
import com.example.zerobaselogin.user.exception.UserNotFoundException;
import com.example.zerobaselogin.user.model.ResponseMessage;
import com.example.zerobaselogin.user.model.UserSearch;
import com.example.zerobaselogin.user.model.UserStatusInput;
import com.example.zerobaselogin.user.repository.UserLoginHistoryRepository;
import com.example.zerobaselogin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ApiAdminUserController {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;
    private final UserLoginHistoryRepository userLoginHistoryRepository;

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

    // 사용자 목록 조회에 대한 검색을 리턴하는 API
    @GetMapping("/api/admin/user/search")
    public ResponseEntity<?> findUser(@RequestBody UserSearch userSearch) {

        // email like '%' || email || '%' -> Oracle
        // email like concat('%', email, '%') -> mySQL

        List<User> userList = userRepository.findByEmailContainsOrPhoneContainsOrUserNameContains(
                userSearch.getEmail(),
                userSearch.getPhone(),
                userSearch.getUserName());

        return ResponseEntity.ok().body(ResponseMessage.success(userList));
    }

    // 사용자 상태 변경 API
    @PatchMapping("/api/admin/user/{id}/status")
    public ResponseEntity<?> userStatus(@PathVariable("id") Long id, @RequestBody UserStatusInput userStatusInput) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();

        user.setStatus(userStatusInput.getStatus());
        userRepository.save(user);

        return ResponseEntity.ok().build();

    }

    // 사용자 정보 삭제 API
    @DeleteMapping("/api/admin/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();
        if (noticeRepository.countByUser(user) > 0) {
            return new ResponseEntity<>(ResponseMessage.fail("사용자가 작성한 공지사항이 있습니다."), HttpStatus.BAD_REQUEST);
        }
        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

    // 접속 이력 조회 API
    @GetMapping("/api/admin/user/{id}/login/history")
    public ResponseEntity<?> userLoginHistory(@PathVariable("id") Long id) {
        List<UserLoginHistory> userLoginHistories = userLoginHistoryRepository.findAll();

        return ResponseEntity.ok().body(userLoginHistories);

    }

    // 사용자 접속 제한 API
    @PatchMapping("/api/admin/user/{id}/lock")
    public ResponseEntity<?> userLock(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();

        if (user.isLockYn()) {
            return new ResponseEntity<>(ResponseMessage.fail("이미 접속제한이 된 사용자입니다."), HttpStatus.BAD_REQUEST);
        }

        user.setLockYn(true);
        userRepository.save(user);

        return ResponseEntity.ok().body(ResponseMessage.success());

    }
}
