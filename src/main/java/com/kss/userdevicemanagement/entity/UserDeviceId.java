package com.kss.userdevicemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeviceId implements Serializable {
    private static final long serialVersionUID = -4063348372293502437L;
    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;
    @Column(name = "device_info_id", nullable = false, length = 100)
    private String deviceInfoId;

}