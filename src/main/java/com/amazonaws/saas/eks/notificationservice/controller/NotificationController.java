package com.amazonaws.saas.eks.notificationservice.controller;

import com.amazonaws.saas.eks.notificationservice.domain.dto.request.NotificationRequest;
import com.amazonaws.saas.eks.notificationservice.domain.dto.request.SubscribeToTopicRequest;
import com.amazonaws.saas.eks.notificationservice.service.NotificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    private static final Logger LOGGER = LogManager.getLogger(NotificationController.class);

    @PostMapping("/topic")
    public String createTopic(@RequestParam String topicName) {
        try {
            return notificationService.createTopic(topicName);
        } catch (Exception e) {
            LOGGER.error("Error creating new topic.", e);
            throw e;
        }
    }

    @PostMapping("/publish")
    public void publishNotification(@RequestBody NotificationRequest request) {
        try {
            notificationService.publishNotification(request);
        } catch (Exception e) {
            LOGGER.error("Error publishing new notification.", e);
            throw e;
        }
    }

    @PostMapping("/subscribe")
    public void subscribePhoneNumber(@RequestBody SubscribeToTopicRequest request) {
        try {
            notificationService.subscribeToTopic(request);
        } catch (Exception e) {
            LOGGER.error("Error subcribing new endpoint.", e);
            throw e;
        }
    }
}
