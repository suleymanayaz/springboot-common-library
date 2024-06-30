package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;

public class BadGatewayException extends CommonException {
    public BadGatewayException(String message) {
        super(HttpStatus.BAD_GATEWAY, message);
    }
}