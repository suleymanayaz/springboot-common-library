package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends CommonException {
    public InternalServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}