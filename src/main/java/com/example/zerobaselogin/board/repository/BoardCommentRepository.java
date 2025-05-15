package com.example.zerobaselogin.board.repository;

import com.example.zerobaselogin.board.entity.BoardComment;
import com.example.zerobaselogin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

    List<BoardComment> findByUser(User user);
}
