package com.kss.userdevicemanagement.service;

import com.kss.userdevicemanagement.common.EnumCodeResponse;
import com.kss.userdevicemanagement.entity.UserTopic;
import com.kss.userdevicemanagement.event.publisher.RefreshUserTopicCachePublisher;
import com.kss.userdevicemanagement.ex.ApiException;
import com.kss.userdevicemanagement.model.request.SubscribeTopicRequest;
import com.kss.userdevicemanagement.repository.TopicRepository;
import com.kss.userdevicemanagement.repository.UserTopicRepository;
import com.kss.userdevicemanagement.repository.redis.UserTopicCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserTopicRepository userTopicRepository;

    @Autowired
    UserTopicCacheRepository userTopicCacheRepository;

    @Autowired
    RefreshUserTopicCachePublisher refreshUserTopicCachePublisher;


    public void subscribeTopic(SubscribeTopicRequest subscribeTopicRequest) throws ApiException {
        LocalDateTime now = LocalDateTime.now();
        String id = UUID.randomUUID().toString();
        if (topicRepository.existsByTopicNameAndStatus(subscribeTopicRequest.getTopicName(), true)) {
            Optional<UserTopic> optionalUserTopic = userTopicRepository.findTopByTopicNameAndUserId(subscribeTopicRequest.getTopicName(), subscribeTopicRequest.getUserId());
            optionalUserTopic.ifPresentOrElse(userTopic -> {
                if (!userTopic.getStatus().equals(subscribeTopicRequest.getSubscribe())) {
                    userTopic.setStatus(subscribeTopicRequest.getSubscribe());
                    userTopic.setUpdateTime(now);
                    userTopicRepository.saveAndFlush(userTopic);
                    refreshUserTopicCachePublisher.refreshUserTopicEvent(userTopic);
                }
            }, () -> {
                UserTopic userTopic = UserTopic.builder()
                        .id(id)
                        .userId(subscribeTopicRequest.getUserId())
                        .topicName(subscribeTopicRequest.getTopicName())
                        .status(subscribeTopicRequest.getSubscribe())
                        .createTime(now)
                        .build();

                userTopicRepository.saveAndFlush(userTopic);
                log.info("subscribeTopic with id : " + id);
                refreshUserTopicCachePublisher.refreshUserTopicEvent(userTopic);
            });
        } else {
            log.error("topic doesn't exists");
            throw new ApiException(EnumCodeResponse.TOPIC_INVALID);
        }
    }

}
