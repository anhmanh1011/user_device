package com.kss.userdevicemanagement.repository;

import com.kss.userdevicemanagement.entity.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, String> {
}