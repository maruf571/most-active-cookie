package com.marufh.cookiefinder.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message, String path) {
        super(String.format(message, path));
    }
}
