package com.example.zerobaselogin.user.repository;

import com.example.zerobaselogin.user.entity.UserLoginHistory;
import com.example.zerobaselogin.user.entity.UserPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserPointRepository extends JpaRepository<UserPoint, Long> {


}
