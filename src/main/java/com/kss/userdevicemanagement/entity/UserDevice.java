package com.kss.userdevicemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_device")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDevice {
    @EmbeddedId
    private UserDeviceId id;

    @Column(name = "is_active_notify", nullable = false)
    private Boolean isActiveNotify = true;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;


}