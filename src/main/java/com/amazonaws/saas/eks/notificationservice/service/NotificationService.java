package com.amazonaws.saas.eks.notificationservice.service;

import com.amazonaws.saas.eks.notificationservice.domain.dto.request.NotificationRequest;
import com.amazonaws.saas.eks.notificationservice.domain.dto.request.SubscribeToTopicRequest;

public interface NotificationService {

    /**
     * Creates an SNS topic with the specified name.
     * @param topicName The name of the topic to create.
     * @return The ARN of the created topic as a {@link String}.
     */
    String createTopic(String topicName);

    /**
     * Sends a notification.
     * @param request {@link NotificationRequest} containing message details.
     */
    void publishNotification(NotificationRequest request);

    /**
     * Subscribes a specified endpoint to the topic.
     * @param request {@link SubscribeToTopicRequest} containing subscription details, such as endpoint and protocol.
     */
    void subscribeToTopic(SubscribeToTopicRequest request);


}