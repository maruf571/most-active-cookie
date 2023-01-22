package com.marufh.cookiefinder.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message, String argument) {
        super(String.format(message, argument));
    }
}
