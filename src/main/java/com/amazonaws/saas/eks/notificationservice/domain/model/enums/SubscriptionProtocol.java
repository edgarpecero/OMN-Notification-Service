package com.amazonaws.saas.eks.notificationservice.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum SubscriptionProtocol {
    EMAIL("email"),
    SMS("sms");

    private final String label;

    SubscriptionProtocol(String label) {
        this.label = label;
    }

    private static final Map<String, SubscriptionProtocol> BY_LABEL = new HashMap<>();

    static {
        for (SubscriptionProtocol e: values()) {
            BY_LABEL.put(e.label, e);
        }
    }

    @JsonCreator
    public static SubscriptionProtocol valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    @Override
    public String toString() {
        return label;
    }
}
