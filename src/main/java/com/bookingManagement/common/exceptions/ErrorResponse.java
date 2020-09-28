package com.bookingManagement.common.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Object representing an Error to be sent to the API caller incase of an Error.
 *
 * @author malav
 */
@Data
@Builder
@SuppressWarnings({"checkstyle:javadocmethod"})
class ErrorResponse {

    private final HttpStatus status;
    private final List<String> errors;

    private String description;
}
