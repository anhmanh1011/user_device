package com.kss.userdevicemanagement.service;

import com.kss.userdevicemanagement.common.EnumCodeResponse;
import com.kss.userdevicemanagement.entity.DeviceInfo;
import com.kss.userdevicemanagement.entity.UserDevice;
import com.kss.userdevicemanagement.entity.UserDeviceId;
import com.kss.userdevicemanagement.ex.ApiException;
import com.kss.userdevicemanagement.model.request.DeviceRegisterRequest;
import com.kss.userdevicemanagement.model.response.DeviceRegisterResponse;
import com.kss.userdevicemanagement.repository.DeviceInfoRepository;
import com.kss.userdevicemanagement.repository.UserDeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class UserDeviceManagementService {

    @Autowired
    DeviceInfoRepository deviceInfoRepository;

    @Autowired
    UserDeviceRepository userDeviceRepository;

    public DeviceRegisterResponse registerDevice(DeviceRegisterRequest deviceRegisterRequest) {
        DeviceRegisterResponse.DeviceRegisterResponseBuilder builder = DeviceRegisterResponse.builder();
        deviceInfoRepository.findById(deviceRegisterRequest.getDeviceId()).ifPresentOrElse(deviceInfo -> builder
                .deviceId(deviceInfo.getDeviceId())
                .id(deviceInfo.getId())
                .isNewDevice(false)
                .build(), () -> {
            DeviceInfo deviceInfo2 = DeviceInfo.builder()
                    .id(UUID.randomUUID().toString())
                    .deviceId(deviceRegisterRequest.getDeviceId())
                    .deviceName(deviceRegisterRequest.getDeviceName())
                    .os(deviceRegisterRequest.getOs())
                    .macAddress(deviceRegisterRequest.getMacAddress())
                    .publicIp(deviceRegisterRequest.getPublicIp())
                    .token(deviceRegisterRequest.getToken())
                    .createTime(LocalDateTime.now())
                    .build();

            DeviceInfo info = deviceInfoRepository.saveAndFlush(deviceInfo2);
            builder.deviceId(info.getDeviceId())
                    .id(info.getId())
                    .isNewDevice(true)
                    .build();
        });
        return builder.build();

//        DeviceInfo deviceInfo = DeviceInfo.builder()
//                .id(UUID.randomUUID().toString())
//                .deviceId(deviceRegisterRequest.getDeviceId())
//                .deviceName(deviceRegisterRequest.getDeviceName())
//                .os(deviceRegisterRequest.getOs())
//                .macAddress(deviceRegisterRequest.getMacAddress())
//                .publicIp(deviceRegisterRequest.getPublicIp())
//                .token(deviceRegisterRequest.getToken())
//                .createTime(LocalDateTime.now())
//                .build();
//
//        DeviceInfo info = deviceInfoRepository.saveAndFlush(deviceInfo);
//
//        return DeviceRegisterResponse.builder()
//                .deviceId(info.getDeviceId())
//                .id(info.getId())
//                .isNewDevice(true)
//                .build();
    }

    public void userDeviceRegister(String userId, String deviceUUID) throws ApiException {
        LocalDateTime now = LocalDateTime.now();
       if( deviceInfoRepository.existsById(deviceUUID)) {
           UserDeviceId userDeviceId = new UserDeviceId(userId, deviceUUID);
           userDeviceRepository.findById(userDeviceId).ifPresentOrElse(userDevice -> log.info("user,device does exists "), () -> {
               UserDevice userDevice = UserDevice.builder()
                       .id(userDeviceId)
                       .isActiveNotify(true)
                       .status(true)
                       .createTime(now)
                       .build();
               userDeviceRepository.saveAndFlush(userDevice);
               log.info("register success ");
           });
       }else {
           log.error("device is not registered");
           throw new ApiException(EnumCodeResponse.DEVICE_INVALID);
       }

    }

}
