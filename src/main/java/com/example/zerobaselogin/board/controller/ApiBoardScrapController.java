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
@RequestMapping("/api")
public class ApiBoardScrapController {

    private final BoardService boardService;

    // 게시글 스크랩 API
    @PutMapping("/board/{id}/scrap")
    public ResponseEntity<?> boardScrap(@PathVariable("id") Long id,
                           @RequestHeader("F-TOKEN") String token) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        return ResponseResult.result(boardService.scrap(id, email));

    }

    // 게시글의 스크랩을 삭제하는 API
    @DeleteMapping("/scrap/{id}")
    public ResponseEntity<?> deleteBoardScrap(@PathVariable("id") Long id,
                                 @RequestHeader("F-TOKEN") String token) {
        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        return ResponseResult.result(boardService.removeScrap(id, email));
    }
}
