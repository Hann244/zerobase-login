package com.example.zerobaselogin.user.model;

import com.example.zerobaselogin.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponse {

    private Long id;
    private String email;
    private String userName;
    protected String phone;

//    public UserResponse(User user) {
//        this.id = user.getId();
//        this.email = user.getEmail();
//        this.userName = user.getUserName();
//        this.phone = user.getPhone();
//    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .phone(user.getPhone())
                .build();
    }
}
