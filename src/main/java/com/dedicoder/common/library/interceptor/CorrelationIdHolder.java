package com.dedicoder.common.library.interceptor;

import java.util.UUID;

public class CorrelationIdHolder {
    private static final ThreadLocal<String> correlationIdHolder = new ThreadLocal<>();

    public static String getCorrelationId() {
        String correlationId = correlationIdHolder.get();
        if (correlationId == null) {
            correlationId = generateCorrelationId();
            setCorrelationId(correlationId);
        }
        return correlationId;
    }

    public static void setCorrelationId(String correlationId) {
        correlationIdHolder.set(correlationId);
    }

    public static void clearCorrelationId() {
        correlationIdHolder.remove();
    }

    private static String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}