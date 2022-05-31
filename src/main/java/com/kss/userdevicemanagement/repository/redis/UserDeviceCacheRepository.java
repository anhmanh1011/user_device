package com.kss.userdevicemanagement.repository.redis;

import com.kss.userdevicemanagement.entity.redis.UserDeviceCacheEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserDeviceCacheRepository extends CrudRepository<UserDeviceCacheEntity, String> {
}