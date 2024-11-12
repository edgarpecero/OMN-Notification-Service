package com.amazonaws.saas.eks.notificationservice.service.impl;

import com.amazonaws.saas.eks.notificationservice.config.SESConfig;
import com.amazonaws.saas.eks.notificationservice.service.SESService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
public class SESServiceImpl implements SESService {
    private static final Logger LOGGER = LogManager.getLogger(SESServiceImpl.class);

    private final String senderEmail;
    private final SesClient sesClient;

    public SESServiceImpl(SesClient sesClient, SESConfig sesConfig) {
        this.senderEmail = sesConfig.getSenderEmail();
        this.sesClient = sesClient;
    }

    @Override
    public void registerEmail(String emailAddress) {
        VerifyEmailIdentityRequest request = VerifyEmailIdentityRequest.builder()
                .emailAddress(emailAddress)
                .build();
        sesClient.verifyEmailIdentity(request);
        System.out.println("Verification email sent to " + emailAddress);
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        Destination destination = Destination.builder()
                .toAddresses(to)
                .build();

        Content subjectContent = Content.builder()
                .data(subject)
                .build();

        Content bodyContent = Content.builder()
                .data(body)
                .build();

        Body emailBody = Body.builder()
                .html(bodyContent)
                .build();

        Message message = Message.builder()
                .subject(subjectContent)
                .body(emailBody)
                .build();

        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .source(senderEmail)
                .destination(destination)
                .message(message)
                .build();

        try {
            sesClient.sendEmail(emailRequest);
            System.out.println("Email sent successfully to " + to);
        } catch (SesException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
