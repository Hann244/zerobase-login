package com.example.zerobaselogin.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNoticeCount {

    private Long id;
    private String email;
    private String userName;

    private long noticeCount;
}
