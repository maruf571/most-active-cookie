package com.marufh.cookiefinder.parser;

import com.marufh.cookiefinder.validator.CommandLineInputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvLogFileParserTest {

    private LogFileParser logFileParser;
    File logFile;

    @BeforeEach
    void init() {
        logFileParser = new CsvLogFileParser(new CommandLineInputValidator());
        logFile = new File("cookie_log.csv");
    }

    @Test
    void testParseCsvFile() {

        Map<String, Long> cookieMap = logFileParser.parse(logFile.getAbsolutePath(), LocalDate.of(2018, 12, 9));
        assertEquals(3, cookieMap.entrySet().size());

        cookieMap = logFileParser.parse(logFile.getAbsolutePath(), LocalDate.of(2018, 12, 8));
        assertEquals(3, cookieMap.entrySet().size());
    }

    @Test
    void testParseCsvFileNoMath() {

        Map<String, Long> cookieMap = logFileParser.parse(logFile.getAbsolutePath(), LocalDate.of(2012, 12, 9));
        assertEquals(0, cookieMap.entrySet().size());
    }
}
