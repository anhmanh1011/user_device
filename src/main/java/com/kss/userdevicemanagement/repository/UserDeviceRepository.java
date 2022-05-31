package com.kss.userdevicemanagement.repository;

import com.kss.userdevicemanagement.entity.UserDevice;
import com.kss.userdevicemanagement.entity.UserDeviceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice, UserDeviceId> {
}