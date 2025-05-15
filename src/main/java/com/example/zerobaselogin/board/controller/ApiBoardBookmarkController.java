package com.example.zerobaselogin.board.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.zerobaselogin.board.service.BoardService;
import com.example.zerobaselogin.common.model.ResponseResult;
import com.example.zerobaselogin.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiBoardBookmarkController {

    private final BoardService boardService;

    // 게시글 북마크 추가 API
    @PutMapping("/board/{id}/bookmark")
    public ResponseEntity<?> boardBookmark(@PathVariable("id") Long id,
                           @RequestHeader("F-TOKEN") String token) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        return ResponseResult.result(boardService.addBookmark(id, email));

    }

    // 게시글의 북마크를 삭제하는 API
    @DeleteMapping("/bookmark/{id}")
    public ResponseEntity<?> deleteBookmark(@PathVariable("id") Long id,
                                 @RequestHeader("F-TOKEN") String token) {
        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        return ResponseResult.result(boardService.removeBookmark(id, email));
    }
}
