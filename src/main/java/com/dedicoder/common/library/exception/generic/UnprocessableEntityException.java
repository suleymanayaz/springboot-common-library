package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends CommonException {
    public UnprocessableEntityException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
}