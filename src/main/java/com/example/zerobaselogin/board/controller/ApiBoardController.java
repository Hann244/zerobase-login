package com.example.zerobaselogin.board.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
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
@RequestMapping("/api/board")
public class ApiBoardController {

    private final BoardService boardService;


    // 게시판 타입을 추가하는 API
    @PostMapping("/type")
    public ResponseEntity<?> addBoardType(@RequestBody @Valid BoardTypeInput boardTypeInput, Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrors = ResponseError.of(errors.getAllErrors());
            return new ResponseEntity<>(ResponseMessage.fail("입력값이 정확하지 않습니다.", responseErrors), HttpStatus.BAD_REQUEST);

        }

        ServiceResult result = boardService.addBoard(boardTypeInput);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().build();
    }

    // 게시판 타입명을 수정하는 API
    @PutMapping("/type/{id}")
    public ResponseEntity<?> updateBoardType(@PathVariable("id") Long id, @RequestBody @Valid BoardTypeInput boardTypeInput, Errors errors) {
        if (errors.hasErrors()) {
            List<ResponseError> responseErrors = ResponseError.of(errors.getAllErrors());
            return new ResponseEntity<>(ResponseMessage.fail("입력값이 정확하지 않습니다.", responseErrors), HttpStatus.BAD_REQUEST);
        }

        ServiceResult result = boardService.updateBoard(id, boardTypeInput);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().build();
    }

    // 게시판 타입을 삭제하는 API
    @DeleteMapping("/type/{id}")
    public ResponseEntity<?> deleteBoardType(@PathVariable("id") Long id) {

        ServiceResult result = boardService.deleteBoard(id);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().body(ResponseMessage.success());

    }

    // 게시판 목록 리턴 API
    @GetMapping("/type")
    public ResponseEntity<?> boardType() {
        List<BoardType> boardTypeList = boardService.getAllBoardType();

        return ResponseEntity.ok().body(ResponseMessage.success(boardTypeList));

    }

    // 게시판 타입의 사용여부를 설정하는 API
    @PatchMapping("/type/{id}/using")
    public ResponseEntity<?> usingBoardType(@PathVariable("id") Long id, @RequestBody BoardTypeUsing boardTypeUsing) {
        ServiceResult result = boardService.setBoardTypeUsing(id, boardTypeUsing);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().body(ResponseMessage.success());

    }

    // 게시판별 작성된 게시글의 개수를 리턴하는 API -> 버전 차이
//    @GetMapping("/type/count")
//    public ResponseEntity<?> boardTypeCount() {
//
//        List<BoardTypeCount> list = boardService.getBoardTypeCount();
//        return ResponseEntity.ok().body(list);
//
//    }

    // 게시된 게시글을 최상단에 배치하는 API
    @PatchMapping("/{id}/top")
    public ResponseEntity<?> boardPostTop(@PathVariable("id") Long id) {
        ServiceResult result = boardService.setBoardTop(id, true);
        return ResponseEntity.ok().body(result);
    }

    // 게시된 게시글을 최상단에서 해제하는 API
    @PatchMapping("/{id}/top/clear")
    public ResponseEntity<?> boardPostTopClear(@PathVariable("id") Long id) {
        ServiceResult result = boardService.setBoardTop(id, false);
        return ResponseEntity.ok().body(result);
    }

    // 게시글의 게시기간을 시작일과 종료일로 설정하는 API
    @PatchMapping("/{id}/publish")
    public ResponseEntity<?> boardPariod(@PathVariable("id") Long id, @RequestBody BoardPeriod boardPeriod) {

        ServiceResult result = boardService.setBoardPeriody(id, boardPeriod);

        if (!result.isResult()) {
            return ResponseResult.fail(result.getMessage());
        }

        return ResponseResult.succeess();

    }

    // 게시글의 조회수를 증가시키는 API
    @PutMapping("/{id}/hits")
    public ResponseEntity<?> boardHits(@PathVariable("id") Long id,
                          @RequestHeader("F-TOKEN") String token) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        ServiceResult result = boardService.setBoardHits(id, email);
        if (result.isFail()) {
            return ResponseResult.fail(result.getMessage());
        }

        return ResponseResult.succeess();

    }

    // 게시글에 대한 좋아요하기 기능을 수행하는 API
    @PutMapping("/{id}/like")
    public ResponseEntity<?> boardLike(@PathVariable("id") Long id,
                          @RequestHeader("F-TOKEN") String token) {
        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        ServiceResult result = boardService.setBoardLike(id, email);
        return ResponseResult.result(result);
    }
}
