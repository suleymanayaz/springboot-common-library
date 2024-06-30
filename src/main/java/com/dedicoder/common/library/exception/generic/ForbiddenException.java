package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;


public class ForbiddenException extends CommonException {
    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
