package com.example.zerobaselogin.board.repository;


import com.example.zerobaselogin.board.entity.BoardType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardTypeRepository extends JpaRepository<BoardType, Long> {
    BoardType findByBoardName(String name);
}
