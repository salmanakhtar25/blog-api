package com.blog.api.exceptions;

import com.blog.api.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException rx){
        String message = rx.getMessage();
        return new ResponseEntity<>(new ApiResponse(message,false), HttpStatus.NOT_FOUND);
    }
}
