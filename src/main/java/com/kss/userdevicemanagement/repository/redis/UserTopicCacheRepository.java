package com.kss.userdevicemanagement.repository.redis;

import com.kss.userdevicemanagement.entity.redis.UserTopicCacheEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserTopicCacheRepository extends CrudRepository<UserTopicCacheEntity, String> {
}