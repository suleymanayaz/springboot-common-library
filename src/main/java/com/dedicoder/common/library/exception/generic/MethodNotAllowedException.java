package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;

public class MethodNotAllowedException extends CommonException {
    public MethodNotAllowedException(String message) {
        super(HttpStatus.METHOD_NOT_ALLOWED, message);
    }
}
