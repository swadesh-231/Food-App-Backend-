package com.course.bengalifoodapp.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
