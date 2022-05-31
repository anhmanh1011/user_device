package com.kss.userdevicemanagement.repository;

import com.kss.userdevicemanagement.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
    boolean existsByIdEqualsAndStatusEquals(String id, Boolean status);
}