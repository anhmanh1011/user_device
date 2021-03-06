package com.kss.userdevicemanagement.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SubscribeTopicRequest {
    @JsonIgnore
    String userId;
    @JsonProperty("topic_name")
    @NotBlank
    String topicName;
    Boolean subscribe = true;
}
