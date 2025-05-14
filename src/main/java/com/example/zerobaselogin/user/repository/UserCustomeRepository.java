package com.example.zerobaselogin.user.repository;

import com.example.zerobaselogin.user.model.UserNoticeCount;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserCustomeRepository {

    private final EntityManager entityManager;

    public List<UserNoticeCount> findUserNoticeCount() {

        String sql = " select u.id, u.email, u.user_name, (select count(*) from Notice n where n.user_id = u.id) notice_count from Users u ";

        List<UserNoticeCount> list = entityManager.createNativeQuery(sql).getResultList();
        return list;
    }
}
