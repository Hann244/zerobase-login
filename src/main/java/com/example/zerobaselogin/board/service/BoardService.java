package com.example.zerobaselogin.board.service;

import com.example.zerobaselogin.board.entity.BoardType;
import com.example.zerobaselogin.board.model.BoardTypeInput;
import com.example.zerobaselogin.board.model.ServiceResult;
import jakarta.validation.Valid;

import java.util.List;

public interface BoardService {

    ServiceResult addBoard(BoardTypeInput boardTypeInput);

    ServiceResult updateBoard(Long id, BoardTypeInput boardTypeInput);

    ServiceResult deleteBoard(Long id);

    List<BoardType> getAllBoardType();
}