package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;

public class TooManyRequestsException extends CommonException {
    public TooManyRequestsException(String message) {
        super(HttpStatus.TOO_MANY_REQUESTS, message);
    }
}