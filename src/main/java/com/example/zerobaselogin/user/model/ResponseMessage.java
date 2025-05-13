package com.example.zerobaselogin.user.model;

import com.example.zerobaselogin.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    private long totalCount;
    private List<User> data;
}
