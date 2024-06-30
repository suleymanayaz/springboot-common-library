package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;

public class GoneException extends CommonException {
    public GoneException(String message) {
        super(HttpStatus.GONE, message);
    }
}