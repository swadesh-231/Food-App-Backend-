package com.course.bengalifoodapp.exception;


import com.course.bengalifoodapp.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException ex) {
        ErrorResponse message = ErrorResponse.builder()
                .message(ex.getMessage()).code(String.valueOf(HttpStatus.NOT_FOUND.value())).build();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
