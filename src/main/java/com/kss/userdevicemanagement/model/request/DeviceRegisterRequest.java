package com.kss.userdevicemanagement.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeviceRegisterRequest {
    @JsonProperty("device_id")
    String deviceId;
    @JsonProperty("device_name")
    String deviceName;
    @JsonProperty("mac_address")
    String macAddress;
    String os;
    String token;
    @JsonIgnore
    String publicIp;
}
