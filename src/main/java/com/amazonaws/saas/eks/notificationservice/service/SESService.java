package com.amazonaws.saas.eks.notificationservice.service;

public interface SESService {

    /**
     * Method to register an email address (only necessary in SES sandbox environment).
     * Verifies a recipient email by sending a verification email.
     *
     * @param emailAddress Recipient email address to verify.
     */
    public void registerEmail(String emailAddress);

    /**
     * Method to send an email using AWS SES.
     *
     * @param to Recipient's email address.
     * @param subject Email subject.
     * @param body HTML content for the email.
     */
    public void sendEmail(String to, String subject, String body);
}