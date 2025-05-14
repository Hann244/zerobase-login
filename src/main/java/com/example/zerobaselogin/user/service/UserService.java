package com.example.zerobaselogin.user.service;

import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.model.UserLogCount;
import com.example.zerobaselogin.user.model.UserNoticeCount;
import com.example.zerobaselogin.user.model.UserSummary;

import java.util.List;

public interface UserService {

    UserSummary getUserStatusCount();

    List<User> getTodayUsers();

    List<UserNoticeCount> getUserNoticeCount();

    List<UserLogCount> getUserLogCount();

    // 좋아요를 가장 많이 한 사용자 목록 리턴
    List<UserLogCount> getUserLikeBest();
}
