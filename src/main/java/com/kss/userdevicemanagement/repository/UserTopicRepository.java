package com.kss.userdevicemanagement.repository;

import com.kss.userdevicemanagement.entity.UserTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTopicRepository extends JpaRepository<UserTopic, String> {
    boolean existsByTopicNameEqualsAndUserIdEquals(String topicName, String userId);

    Optional<UserTopic> findTopByTopicNameAndUserId(String topicName, String userId);

}