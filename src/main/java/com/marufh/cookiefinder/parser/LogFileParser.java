package com.marufh.cookiefinder.parser;

import java.time.LocalDate;
import java.util.Map;

public interface  LogFileParser {
    Map<String, Long> parse(String file, LocalDate givenDate);
}
