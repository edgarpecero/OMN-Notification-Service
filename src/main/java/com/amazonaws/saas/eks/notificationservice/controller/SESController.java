package com.amazonaws.saas.eks.notificationservice.controller;

import com.amazonaws.saas.eks.notificationservice.service.SESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications/ses")
public class SESController {

    @Autowired
    private SESService sesService;

    @GetMapping("/register-email")
    public String registerEmail(@RequestParam String email) {
        sesService.registerEmail(email);
        return "Verification email sent to " + email;
    }

    @GetMapping("/send-email")
    public String sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {

        sesService.sendEmail(to, subject, body);
        return "Email sent to " + to;
    }

    /**
     * Heartbeat method to check if ses notification service is up and running
     *
     */
    @RequestMapping("/health")
    public String health() {
        return "\"SES notification service is up!\"";
}
