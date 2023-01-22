package com.marufh.cookiefinder.exception;

public interface ErrorMessage {
    String FILE_NOT_FOUND = "'%s' file not found";
    String INVALID_FILE_NAME = "'%s' not a valid file name";
    String INVALID_FILE_EXTENSION = "'%s' is not valid file extension";
    String INVALID_DATE = "'%s' is not a valid date";
    String FILE_READ_ERROR = "Error while reading file '%s'";
    String FILE_ARGUMENT_NOT_FOUND = "File argument not found, %s";
    String DATE_ARGUMENT_NOT_FOUND = "Date argument not found,  %s";
}
