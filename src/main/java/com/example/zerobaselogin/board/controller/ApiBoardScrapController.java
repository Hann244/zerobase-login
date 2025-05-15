package com.example.zerobaselogin.board.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.zerobaselogin.board.entity.BoardBadReport;
import com.example.zerobaselogin.board.service.BoardService;
import com.example.zerobaselogin.common.model.ResponseResult;
import com.example.zerobaselogin.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class ApiBoardScrapController {

    private final BoardService boardService;

    // 게시글 스크랩 API
    @PutMapping("/{id}/scrap")
    public ResponseEntity<?> boardScrap(@PathVariable("id") Long id,
                           @RequestHeader("F-TOKEN") String token) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        return ResponseResult.result(boardService.scrapBoard(id, email));

    }
}
