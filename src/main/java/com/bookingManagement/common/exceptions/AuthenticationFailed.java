package com.bookingManagement.common.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
@Getter
public class AuthenticationFailed extends RuntimeException {
    private Integer code;

    public AuthenticationFailed() {
    }

    public AuthenticationFailed(String message) {
        super(message);
    }

    public AuthenticationFailed(String message, int errorCode) {
        super(message);
        this.code = errorCode;
    }
}