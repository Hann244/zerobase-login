package com.example.zerobaselogin.board.entity;


import com.example.zerobaselogin.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardBadReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 신고자 정보
    @Column
    private Long userId;

    @Column
    private String userName;

    @Column
    private String userEmail;

    // 신고 게시글 정보
    @Column
    private Long boardId;

    @Column
    private Long boardUserId;

    @Column
    private String boardTitle;

    @Column
    private String boardContents;

    @Column
    private LocalDateTime boardRegDate;

    // 신고 내용
    @Column
    private String comments;
    // 신고 날짜
    @Column
    private LocalDateTime regDate;
}
