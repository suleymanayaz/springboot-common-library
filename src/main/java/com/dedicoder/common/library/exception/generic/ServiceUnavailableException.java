package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;

public class ServiceUnavailableException extends CommonException {
    public ServiceUnavailableException(String message) {
        super(HttpStatus.SERVICE_UNAVAILABLE, message);
    }
}