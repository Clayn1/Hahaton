package com.pivo.hahatonbe.rest.repository;

import com.pivo.hahatonbe.model.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, Integer> {
    List<UserNotification> findAllByUserId(Integer userId);
}
