package com.dedicoder.common.library.exception;

import java.util.List;

public class ErrorResponse {
    private String correlationId;
    private int statusCode;
    private String message;
    private String detail;
    private List<ErrorResource> errors;

    public ErrorResponse(String correlationId, int statusCode, String message, String detail, List<ErrorResource> errors) {
        this.correlationId = correlationId;
        this.statusCode = statusCode;
        this.message = message;
        this.detail = detail;
        this.errors = errors;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<ErrorResource> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorResource> errors) {
        this.errors = errors;
    }
}
