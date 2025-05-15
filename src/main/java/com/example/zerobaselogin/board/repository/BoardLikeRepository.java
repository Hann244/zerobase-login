package com.example.zerobaselogin.board.repository;

import com.example.zerobaselogin.board.entity.Board;
import com.example.zerobaselogin.board.entity.BoardHits;
import com.example.zerobaselogin.board.entity.BoardLike;
import com.example.zerobaselogin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {


    long countByBoardAndUser(Board board, User user);
    Optional<BoardLike> findByBoardAndUser(Board board, User user);
}
