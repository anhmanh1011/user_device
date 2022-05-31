package com.kss.userdevicemanagement.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_topic")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTopic {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_id", length = 100)
    private String userId;

    @Column(name = "topic_id", length = 100)
    private String topicId;

    @Column(name = "status")
    private Boolean status = true;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

}