package com.kss.userdevicemanagement.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRegisterResponse {
    String deviceId;
    Boolean isNewDevice;
    String id;
}
