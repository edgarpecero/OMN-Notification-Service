package com.amazonaws.saas.eks.notificationservice.domain.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@NotBlank
public class SubscribeToTopicRequest {
    private String email;
    private String phoneNumber;
    //private String topicArn
}