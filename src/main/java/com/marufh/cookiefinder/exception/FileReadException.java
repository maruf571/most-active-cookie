package com.marufh.cookiefinder.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileReadException extends RuntimeException {
    public FileReadException(String message, String path) {
        super(String.format(message, path));
    }
}
