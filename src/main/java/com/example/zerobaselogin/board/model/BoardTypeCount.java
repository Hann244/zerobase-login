package com.example.zerobaselogin.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardTypeCount {

    private Long id;
    private String boardName;
    private LocalDateTime regDate;
    private boolean usingYn;
    private long boardCount;

    public BoardTypeCount(Object[] arrObj) {
        this.id = ((BigInteger) arrObj[0]).longValue();
        this.boardName = (String) arrObj[1];
        this.regDate = (LocalDateTime) arrObj[2];
        this.usingYn = ((Boolean) arrObj[3]);
        this.boardCount = ((BigInteger) arrObj[4]).longValue();
    }

    public BoardTypeCount(BigInteger id, String boardName, LocalDateTime regDate, boolean usingYn, BigInteger boardCount) {
        this.id = id.longValue();
        this.boardName = boardName;
        this.regDate = regDate;
        this.usingYn = usingYn;
        this.boardCount = boardCount.longValue();
    }
}
