package com.example.zerobaselogin.board.service;

import com.example.zerobaselogin.board.entity.BoardType;
import com.example.zerobaselogin.board.model.BoardTypeCount;
import com.example.zerobaselogin.board.model.BoardTypeInput;
import com.example.zerobaselogin.board.model.BoardTypeUsing;
import com.example.zerobaselogin.board.model.ServiceResult;
import jakarta.validation.Valid;

import java.util.List;

public interface BoardService {

    ServiceResult addBoard(BoardTypeInput boardTypeInput);

    ServiceResult updateBoard(Long id, BoardTypeInput boardTypeInput);

    ServiceResult deleteBoard(Long id);

    List<BoardType> getAllBoardType();

    // 게시판 타입의 사용여부 설정
    ServiceResult setBoardTypeUsing(Long id, BoardTypeUsing boardTypeUsing);

    // 게시글을 최상단에 배치
    ServiceResult setBoardTop(Long id, boolean flag);

    // 게시판 타입의 게시글 수를 리턴
    //List<BoardTypeCount> getBoardTypeCount();
}