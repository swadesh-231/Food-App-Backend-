package com.course.bengalifoodapp.exception;

public class FilePathNotFound extends RuntimeException {
    public FilePathNotFound(String message) {
        super(message);
    }

    public FilePathNotFound() {
        super("File Path Is Not Found");
    }
}
