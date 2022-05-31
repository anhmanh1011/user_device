package com.kss.userdevicemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "DeviceInfo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfo {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "mac_addr")
    private String macAddress;

    @Column(name = "os")
    private String os;

    @Column(name = "token")
    private String token;

    @Column(name = "public_ip")
    private String publicIp;

    @Column(name = "create_time")
    private LocalDateTime createTime;

}