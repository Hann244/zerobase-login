package com.example.zerobaselogin.user.service;

import com.example.zerobaselogin.board.model.ServiceResult;
import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.entity.UserPoint;
import com.example.zerobaselogin.user.model.UserPointInput;
import com.example.zerobaselogin.user.model.UserPointType;
import com.example.zerobaselogin.user.repository.UserPointRepository;
import com.example.zerobaselogin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PointServiceImpl implements PointService {

    private  final UserPointRepository userPointRepository;
    private final UserRepository userRepository;

    @Override
    public ServiceResult addPoint(String email, UserPointInput userPointInput) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
        }
        User user = optionalUser.get();

        userPointRepository.save(UserPoint.builder()
                .user(user)
                .userPointType(userPointInput.getUserPointType())
                .point(userPointInput.getUserPointType().getValue())
                .build());

        return ServiceResult.success();
    }
}
