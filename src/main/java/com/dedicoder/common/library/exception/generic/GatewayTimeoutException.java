package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;

public class GatewayTimeoutException extends CommonException {
    public GatewayTimeoutException(String message) {
        super(HttpStatus.GATEWAY_TIMEOUT, message);
    }
}