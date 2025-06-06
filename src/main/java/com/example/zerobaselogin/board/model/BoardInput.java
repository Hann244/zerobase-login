package com.example.zerobaselogin.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardInput {

    private long boardType;
    private String title;
    private String contents;
}
