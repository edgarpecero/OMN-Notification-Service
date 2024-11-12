package com.amazonaws.saas.eks.notificationservice.service.impl;

import com.amazonaws.saas.eks.notificationservice.config.SNSConfig;
import com.amazonaws.saas.eks.notificationservice.domain.model.enums.SubscriptionProtocol;
import com.amazonaws.saas.eks.notificationservice.service.NotificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;


@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger LOGGER = LogManager.getLogger(NotificationServiceImpl.class);

    private final SnsClient snsClient;

    private final String snsEmailTopicARN;
    public NotificationServiceImpl(SnsClient snsClient, SNSConfig snsConfig) {
        this.snsClient = snsClient;
        this.snsEmailTopicARN = snsConfig.getSnsEmailTopicARN();
    }

    @Override
    // Creates a new SNS topic with the specified name.
    public String createTopic(String topicName) {
        try {
            // Build the request to create the topic.
            CreateTopicRequest request = CreateTopicRequest.builder()
                    .name(topicName)
                    .build();

            // Send the create topic request to AWS SNS and get the response.
            CreateTopicResponse result = snsClient.createTopic(request);

            // Return the ARN of the created topic.
            return result.topicArn();
        } catch (Exception e) {
            // Log the error and throw a runtime exception if the topic creation fails.
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Failed creating topic.");
        }
    }

    @Override
    // Publishes a notification message to an SNS topic.
    public void publishNotification(String message, String subject) {
        try {
            // Build the request to publish the notification to the topic.
            PublishRequest publishRequest = PublishRequest.builder()
                    .message(message) // Set the message content.
                    .subject(subject) // Set the subject of the message.
                    .topicArn(snsEmailTopicARN) // Specify the topic ARN.
                    .build();

            // Send the publish request to AWS SNS and get the response.
            PublishResponse publishResponse = snsClient.publish(publishRequest);

            // Log the message ID of the sent notification.
            System.out.println("Status: " + publishResponse.messageId());
        } catch (Exception e) {
            // Log the error and throw a runtime exception if publishing fails.
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Failed sending notification.");
        }
    }

    @Override
    public void subscribeToTopic(String email) {
        try {
            // Determine protocol based on request (email or SMS).
//            String protocol = (request.getEmail() != null) ? SubscriptionProtocol.EMAIL.getLabel() : SubscriptionProtocol.SMS.getLabel();
//            String endpoint = (request.getEmail() != null) ? request.getEmail() : request.getPhoneNumber();

            // TODO: Set topicArn dynamically if needed.
            SubscribeRequest subscribeRequest = SubscribeRequest.builder()
                    .protocol(SubscriptionProtocol.EMAIL.getLabel())
                    .endpoint(email)
                    .topicArn(snsEmailTopicARN)
                    .build();

            // Send subscription request to AWS SNS.
            snsClient.subscribe(subscribeRequest);
        } catch (Exception e) {
            // Log and throw an error if subscription fails.
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Subscription failed.");
        }
    }
}
