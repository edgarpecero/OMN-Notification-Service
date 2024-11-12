package com.amazonaws.saas.eks.notificationservice.utils;

import java.util.UUID;
public class Utils {
    public static String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }
}
