package com.example.zerobaselogin.board.repository;

import com.example.zerobaselogin.board.entity.Board;
import com.example.zerobaselogin.board.entity.BoardHits;
import com.example.zerobaselogin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardHitsRepository extends JpaRepository<BoardHits, Long> {

    long countByBoardAndUser(Board board, User user);

}
