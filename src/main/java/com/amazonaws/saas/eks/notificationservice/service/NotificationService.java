package com.amazonaws.saas.eks.notificationservice.service;

public interface NotificationService {

    /**
     * Creates an SNS topic with the specified name.
     * @param topicName The name of the topic to create.
     * @return The ARN of the created topic as a {@link String}.
     */
    String createTopic(String topicName);

    /**
     * Sends a notification to the given endpoint.
     * @param message the notification content
     * @param subject the notification subject
     */
    void publishNotification(String message, String subject);

    /**
     * Subscribes a specified endpoint to the topic.
     * @param email {@link String} containing subscription details, such as endpoint and protocol.
     */
    void subscribeToTopic(String email);


}