package com.example.zerobaselogin.board.service;

import com.example.zerobaselogin.board.entity.BoardType;
import com.example.zerobaselogin.board.model.BoardTypeInput;
import com.example.zerobaselogin.board.model.ServiceResult;
import com.example.zerobaselogin.board.repository.BoardTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardTypeRepository boardTypeRepository;

    @Override
    public ServiceResult addBoard(BoardTypeInput boardTypeInput) {

        BoardType boardType = boardTypeRepository.findByBoardName(boardTypeInput.getName());

        if (boardType != null && boardTypeInput.getName().equals(boardType.getBoardName())) {
            // 동일한 게시판 제목이 있는 경우
            return ServiceResult.fail("이미 동일한 게시판 제목이 존재합니다.");

        }

        BoardType addBoardType = BoardType.builder()
                .boardName(boardTypeInput.getName())
                .regDate(LocalDateTime.now())
                .build();

        boardTypeRepository.save(addBoardType);

        return ServiceResult.success();
    }

    @Override
    public ServiceResult updateBoard(Long id, BoardTypeInput boardTypeInput) {
        Optional<BoardType> optionalBoardType = boardTypeRepository.findById(id);

        if (!optionalBoardType.isPresent()) {
            return ServiceResult.fail("수정할 게시판타입이 없습니다.");
        }

        BoardType boardType = optionalBoardType.get();

        if (boardTypeInput.getName().equals(boardType.getBoardName())) {
            // 동일한 게시판 제목이 있는 경우
            return ServiceResult.fail("수정할 이름이 동일한 게시판명 입니다.");

        }

        boardType.setBoardName(boardTypeInput.getName());
        boardType.setUpdateDate(LocalDateTime.now());
        boardTypeRepository.save(boardType);

        return ServiceResult.success();
    }
}
