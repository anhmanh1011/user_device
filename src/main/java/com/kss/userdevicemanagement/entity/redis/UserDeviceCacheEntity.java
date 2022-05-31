package com.kss.userdevicemanagement.entity.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@RedisHash(value = "user_device") // 24h
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDeviceCacheEntity implements Serializable {
    private static final long serialVersionUID = 1708925807375596792L;

    @Id
    String userId;
    List<DeviceInfo> devices;

    @Data
    public static class DeviceInfo implements Serializable{
        private static final long serialVersionUID = 1708925807375596793L;

        String deviceId;
        String token;

        public DeviceInfo(String deviceId, String token) {
            this.deviceId = deviceId;
            this.token = token;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
