package com.example.zerobaselogin.board.repository;

import com.example.zerobaselogin.board.entity.BoardBookmark;
import com.example.zerobaselogin.board.entity.BoardScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardBookmarkRepository extends JpaRepository<BoardBookmark, Long> {


}
