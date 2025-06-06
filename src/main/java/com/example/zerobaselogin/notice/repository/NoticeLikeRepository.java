package com.example.zerobaselogin.notice.repository;

import com.example.zerobaselogin.notice.entity.NoticeLike;
import com.example.zerobaselogin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeLikeRepository extends JpaRepository<NoticeLike, Long> {

    List<NoticeLike> findByUser(User user);
}
