package com.kss.userdevicemanagement.event.publisher;

import com.kss.userdevicemanagement.entity.UserTopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RefreshUserTopicCachePublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    public void refreshUserTopicEvent(UserTopic userTopic) {
        log.info("Publishing refreshUserTopicEvent ");
        applicationEventPublisher.publishEvent(userTopic);
    }


}
