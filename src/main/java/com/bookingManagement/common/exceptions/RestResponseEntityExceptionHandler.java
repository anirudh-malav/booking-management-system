package com.bookingManagement.common.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@EnableWebMvc
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {

        final List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());

        final ErrorResponse appError = ErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errors(errors).build();

        return handleExceptionInternal(ex, appError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
