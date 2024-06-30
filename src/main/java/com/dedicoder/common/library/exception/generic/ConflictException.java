package com.dedicoder.common.library.exception.generic;

import org.springframework.http.HttpStatus;

public class ConflictException extends CommonException {
    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}