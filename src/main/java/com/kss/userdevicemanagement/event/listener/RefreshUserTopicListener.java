package com.kss.userdevicemanagement.event.listener;

import com.kss.userdevicemanagement.entity.UserTopic;
import com.kss.userdevicemanagement.entity.redis.UserTopicCacheEntity;
import com.kss.userdevicemanagement.event.RefreshUserTopicCacheEvent;
import com.kss.userdevicemanagement.repository.redis.UserTopicCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class RefreshUserTopicListener implements ApplicationListener<RefreshUserTopicCacheEvent> {

    @Autowired
    UserTopicCacheRepository userTopicCacheRepository;

    @Override
    public void onApplicationEvent(RefreshUserTopicCacheEvent event) {
        UserTopic userTopic = event.getUserTopic();
        log.info("RefreshUserTopicListener: - userTopicCacheEntity " + userTopic.toString());
        Optional<UserTopicCacheEntity> topicCacheEntity = userTopicCacheRepository.findById(userTopic.getTopicName());
        topicCacheEntity.ifPresent(userTopicCacheEntity -> {
            List<String> users = userTopicCacheEntity.getUsers();
            boolean contains = users.contains(userTopic.getUserId());
            if (userTopic.getStatus() && !contains) {
                users.add(userTopic.getUserId());
            } else {
                users.remove(userTopic.getUserId());
            }
            userTopicCacheRepository.save(userTopicCacheEntity);
        });
        log.info("RefreshUserTopicListener:success: " + userTopic.getUserId());

    }
}
