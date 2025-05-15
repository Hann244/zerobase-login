package com.example.zerobaselogin.user.repository;

import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.entity.UserInterest;
import com.example.zerobaselogin.user.entity.UserLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {

    long countByUserAndInterestUser(User user, User interestUser);
}
