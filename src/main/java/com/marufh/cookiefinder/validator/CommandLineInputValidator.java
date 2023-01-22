package com.marufh.cookiefinder.validator;

import com.marufh.cookiefinder.exception.ErrorMessage;
import com.marufh.cookiefinder.exception.InvalidArgumentException;
import com.marufh.cookiefinder.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@Slf4j
@Component
public class CommandLineInputValidator {

    public void validateArgument(ApplicationArguments args) {
        if(!args.containsOption("f")) {
            throw new InvalidArgumentException(ErrorMessage.FILE_ARGUMENT_NOT_FOUND, "please check for the argument f");
        }

        if(!args.containsOption("d")) {
            throw new InvalidArgumentException(ErrorMessage.DATE_ARGUMENT_NOT_FOUND, "please check for the argument d");
        }

        String file = args.getOptionValues("f").get(0);
        if(file.trim().equals("")) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_FILE_NAME, file);
        }

        String dateStr = args.getOptionValues("d").get(0);

        if(dateStr.trim().equals("") || dateStr.length() < 10) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_DATE, dateStr);
        }
    }

    public void validateParserInput(String file, LocalDate givenDate) {
        validateFile(file);
        validateDate(givenDate);
    }

    private void validateFile(String file) {

        if(!FilenameUtils.isExtension(file, "csv")) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_FILE_EXTENSION, FilenameUtils.getExtension(file));
        }

        if(!Files.exists(Path.of(file))) {
            throw new NotFoundException(ErrorMessage.FILE_NOT_FOUND, file);
        }
    }

    private void validateDate(LocalDate givenDate) {
        if(givenDate.isAfter(LocalDate.now())) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_DATE, givenDate.toString());
        }
    }

}
