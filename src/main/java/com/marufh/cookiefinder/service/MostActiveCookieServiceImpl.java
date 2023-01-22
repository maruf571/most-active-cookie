package com.marufh.cookiefinder.service;

import com.marufh.cookiefinder.dto.CommandLineArgument;
import com.marufh.cookiefinder.extractor.CookieExtractor;
import com.marufh.cookiefinder.parser.CommandLineArgumentParser;
import com.marufh.cookiefinder.parser.LogFileParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MostActiveCookieServiceImpl implements MostActiveCookieService {

    private final LogFileParser logFileParser;
    private final CookieExtractor cookieExtractor;
    private final CommandLineArgumentParser commandLineArgumentParser;

    @Override
    public List<String> findTopCountedCookie(ApplicationArguments args) {
        // Map command line argument
        CommandLineArgument commandLineArgument = commandLineArgumentParser.mapToCommandLineArgument(args);

        log.info("Input argument f: {}", commandLineArgument.getFile() );
        log.info("Input argument d: {}", commandLineArgument.getDate() );

        // Parse csv file
        Map<String, Long> cookieCountMap = logFileParser.parse(commandLineArgument.getFile(), commandLineArgument.getDate());

        // Extract most active cookie
        return cookieExtractor.extract(cookieCountMap);
    }

}
