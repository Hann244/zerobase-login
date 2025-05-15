package com.example.zerobaselogin.user.service;

import com.example.zerobaselogin.board.model.ServiceResult;
import com.example.zerobaselogin.user.model.UserPointInput;
import com.example.zerobaselogin.user.model.UserPointType;

public interface PointService {
    ServiceResult addPoint(String email, UserPointInput userPointInput);
}
