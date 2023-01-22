package com.marufh.cookiefinder.parser;

import com.marufh.cookiefinder.dto.CommandLineArgument;
import com.marufh.cookiefinder.validator.CommandLineInputValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandLineArgumentParser {

    private static final String FILE_ARGUMENT = "f";
    private static final String DATE_ARGUMENT = "d";

    private final CommandLineInputValidator commandLineInputValidator;

    public CommandLineArgument mapToCommandLineArgument(ApplicationArguments args) {
        // Validate argument
        commandLineInputValidator.validateArgument(args);

        // Construct CommandLineArgument
        return CommandLineArgument.builder()
                .file(args.getOptionValues(FILE_ARGUMENT).get(0))
                .date(LocalDate.parse(args.getOptionValues(DATE_ARGUMENT).get(0)))
                .build();
    }
}
