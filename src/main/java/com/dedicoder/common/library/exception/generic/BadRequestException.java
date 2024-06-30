package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;

public class BadRequestException extends CommonException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}