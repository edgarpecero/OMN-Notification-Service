package com.amazonaws.saas.eks.notificationservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
@Configuration
public class SNSConfig {
    @Getter
    private String snsEmailTopicARN = "arn:aws:sns:us-west-1:708224731803:OMNEmailNotificationsTopic";

    @Bean
    public SnsClient snsClient(@Value("${aws.region}") final String region) {
        this.snsEmailTopicARN = snsEmailTopicARN;
        return SnsClient.builder().region(Region.of(region)).build();
    }
}