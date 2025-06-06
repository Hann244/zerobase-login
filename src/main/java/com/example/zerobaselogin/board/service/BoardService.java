package com.example.zerobaselogin.board.service;

import com.example.zerobaselogin.board.entity.Board;
import com.example.zerobaselogin.board.entity.BoardBadReport;
import com.example.zerobaselogin.board.entity.BoardComment;
import com.example.zerobaselogin.board.entity.BoardType;
import com.example.zerobaselogin.board.model.*;
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

    // 게시글의 게시기간을 설정
    ServiceResult setBoardPeriody(Long id, BoardPeriod boardPeriod);

    // 게시글의 조회수 증가
    ServiceResult setBoardHits(Long id, String email);

    // 게시글의 좋아요를 함
    ServiceResult setBoardLike(Long id, String email);

    // 게시글의 좋아요를 취소함
    ServiceResult setBoardUnLike(Long id, String email);

    // 게시글을 신고하는 기능
    ServiceResult addBadReport(Long id, String email, BoardBadReportInput boardBadReportInput);

    // 신고된 게시글 정보 목록
    List<BoardBadReport> badReportList();

    // 게시글을 스크랩함
    ServiceResult scrap(Long id, String email);

    // 게시글 스크랩 삭제
    ServiceResult removeScrap(Long id, String email);

    // 북마크 추가
    ServiceResult addBookmark(Long id, String email);

    // 북마크 삭제
    ServiceResult removeBookmark(Long id, String email);

    // 본인이 작성한 게시글 리턴
    List<Board> postList(String email);

    // 본인이 작성한 코멘트 목록 리턴
    List<BoardComment> commentList(String email);

    // 게시글 상세정보
    Board detail(Long id);

    // 게시글 목록
    List<Board> list();

    // 게시글 작성
    ServiceResult add(String email, BoardInput boardInput);

    // 게시판 타입의 게시글 수를 리턴
    //List<BoardTypeCount> getBoardTypeCount();
}