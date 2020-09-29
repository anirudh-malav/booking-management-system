package com.bookingManagement.common.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
class ErrorResponse {

    private final HttpStatus status;
    private final List<String> errors;

    private String description;
}
