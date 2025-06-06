package com.example.zerobaselogin.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusInput {

    // 상태 : 정상, 정지
    private UserStatus status;
}
