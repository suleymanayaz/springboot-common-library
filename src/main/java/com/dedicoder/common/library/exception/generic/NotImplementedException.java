package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;

public class NotImplementedException extends CommonException {
    public NotImplementedException(String message) {
        super(HttpStatus.NOT_IMPLEMENTED, message);
    }
}