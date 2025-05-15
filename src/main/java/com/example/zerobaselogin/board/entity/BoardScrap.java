package com.example.zerobaselogin.board.entity;


import com.example.zerobaselogin.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardScrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 스크랩한 사람의 정보
    @ManyToOne
    @JoinColumn
    private User user;

    // 스크랩 게시글 정보
    @Column
    private Long boardId;

    @Column
    private Long boardTypeId;

    @Column
    private Long boardUserId;

    @Column
    private String boardTitle;

    @Column
    private String boardContents;

    @Column
    private LocalDateTime boardRegDate;

    // 스크랩 날짜
    @Column
    private LocalDateTime regDate;
}
