package com.kss.userdevicemanagement.repository;

import com.kss.userdevicemanagement.entity.UserTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTopicRepository extends JpaRepository<UserTopic, String> {
    boolean existsByTopicIdEqualsAndUserIdEquals(String topicId, String userId);

    @Query("select u from UserTopic u where u.topicId = ?1 and u.userId = ?2 ")
    Optional<UserTopic> findByTopicIdAndUserId(String topicId, String userId);

}