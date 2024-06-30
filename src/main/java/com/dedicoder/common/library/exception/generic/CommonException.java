package com.dedicoder.common.library.exception.generic;
import org.springframework.http.HttpStatus;
public class CommonException extends  RuntimeException{
    private final HttpStatus status;

    public CommonException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
