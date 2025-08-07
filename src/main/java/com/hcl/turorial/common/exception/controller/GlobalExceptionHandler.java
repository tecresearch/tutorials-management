package com.hcl.turorial.common.exception.controller;

import com.hcl.turorial.common.exception.DuplicateResourceException;
import com.hcl.turorial.common.exception.ResourceNotFoundException;
import com.hcl.turorial.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException e, WebRequest request) {
        ErrorResponse errorResponse=new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(false).replace("uri=","")
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResource(DuplicateResourceException e, WebRequest request) {
        ErrorResponse errorResponse=new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.value(),
                HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(false).replace("uri=","")
        );

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponse);
    }
}
