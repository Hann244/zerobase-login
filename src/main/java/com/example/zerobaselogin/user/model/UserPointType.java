package com.example.zerobaselogin.user.model;

public enum UserPointType {
    NONE(0),

    USER_REGISTER(100),

    ADD_POST(200),

    ADD_COMMENT(150),

    ADD_LIKE(50);

    private int value;
    public int getValue() {
        return this.value;
    }

    UserPointType(int value) {
        this.value = value;
    }
}
