package com.example.zerobaselogin.board.service;

import com.example.zerobaselogin.board.model.BoardTypeInput;
import com.example.zerobaselogin.board.model.ServiceResult;

public interface BoardService {

    ServiceResult addBoard(BoardTypeInput boardTypeInput);
}