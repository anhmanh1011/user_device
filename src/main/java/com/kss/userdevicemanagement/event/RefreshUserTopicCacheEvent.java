package com.kss.userdevicemanagement.event;

import com.kss.userdevicemanagement.entity.UserTopic;
import org.springframework.context.ApplicationEvent;

public class RefreshUserTopicCacheEvent extends ApplicationEvent {
    UserTopic userTopic;

    public RefreshUserTopicCacheEvent(Object source, UserTopic userTopic) {
        super(source);
        this.userTopic = userTopic;
    }

    public UserTopic getUserTopic() {
        return userTopic;
    }

    public void setUserTopic(UserTopic userTopic) {
        this.userTopic = userTopic;
    }
}
