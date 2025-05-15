package com.example.zerobaselogin.board.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.zerobaselogin.board.entity.BoardBadReport;
import com.example.zerobaselogin.board.entity.BoardType;
import com.example.zerobaselogin.board.model.*;
import com.example.zerobaselogin.board.service.BoardService;
import com.example.zerobaselogin.common.model.ResponseResult;
import com.example.zerobaselogin.notice.model.ResponseError;
import com.example.zerobaselogin.user.model.ResponseMessage;
import com.example.zerobaselogin.util.JWTUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/board")
public class ApiAdminBoardController {

    private final BoardService boardService;

    // 게시글 신고하기 목록을 조회하는 API
    @GetMapping("/badreport")
    public ResponseEntity<?> barReport() {
        List<BoardBadReport> list = boardService.badReportList();
        return ResponseResult.succeess(list);

    }
}
