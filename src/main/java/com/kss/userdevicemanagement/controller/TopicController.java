package com.kss.userdevicemanagement.controller;

import com.kss.userdevicemanagement.common.ResponseData;
import com.kss.userdevicemanagement.ex.ApiException;
import com.kss.userdevicemanagement.model.request.SubscribeTopicRequest;
import com.kss.userdevicemanagement.service.TopicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Subscribe Topic")
@RestController
public class TopicController {

    @Autowired
    TopicService topicService;

    @PostMapping("/e/subscribe_topic/{user_id}")
    public ResponseData<Void> subscribeTopic(@RequestBody SubscribeTopicRequest subscribeTopicRequest, @PathVariable(value = "user_id") String userId ) throws ApiException {
        subscribeTopicRequest.setUserId(userId);
        topicService.subscribeTopic(subscribeTopicRequest);
        return new ResponseData<>();
    }
}
