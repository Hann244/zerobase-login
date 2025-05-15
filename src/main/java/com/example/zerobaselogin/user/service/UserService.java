package com.example.zerobaselogin.user.service;

import com.example.zerobaselogin.board.model.ServiceResult;
import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.model.UserLogCount;
import com.example.zerobaselogin.user.model.UserLogin;
import com.example.zerobaselogin.user.model.UserNoticeCount;
import com.example.zerobaselogin.user.model.UserSummary;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {

    UserSummary getUserStatusCount();

    List<User> getTodayUsers();

    List<UserNoticeCount> getUserNoticeCount();

    List<UserLogCount> getUserLogCount();

    // 좋아요를 가장 많이 한 사용자 목록 리턴
    List<UserLogCount> getUserLikeBest();

    // 관심 사용자 등록
    ServiceResult addInterestUser(String email, Long id);

    // 관심 사용자 삭제
    ServiceResult removeInterestUser(String email, Long interestId);

    // 로그인 정보 확인
    User login(UserLogin userLogin);
}
