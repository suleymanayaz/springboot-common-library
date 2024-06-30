package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CommonException {
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}