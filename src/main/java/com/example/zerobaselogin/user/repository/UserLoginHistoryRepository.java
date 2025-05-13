package com.example.zerobaselogin.user.repository;

import com.example.zerobaselogin.user.entity.UserLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserLoginHistoryRepository extends JpaRepository<UserLoginHistory, Long> {


}
