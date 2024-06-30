package com.dedicoder.common.library.config;

import java.util.UUID;
import org.apache.logging.log4j.ThreadContext;

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
        ThreadContext.put("correlationId", correlationId);
    }

    public static void clearCorrelationId() {
        correlationIdHolder.remove();
        ThreadContext.remove("correlationId");
    }

    private static String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
