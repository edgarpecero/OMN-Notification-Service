package com.amazonaws.saas.eks.notificationservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
public class SESConfig {
    @Getter
    @Value("${aws.ses.source-email}")
    private String senderEmail;

    @Bean
    public SesClient sesClient(@Value("${aws.region}") final String region) {
        this.senderEmail = senderEmail;
        return SesClient.builder().region(Region.of(region)).build();
    }
}