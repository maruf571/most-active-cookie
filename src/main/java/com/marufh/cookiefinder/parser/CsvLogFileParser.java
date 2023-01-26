package com.marufh.cookiefinder.parser;

import com.marufh.cookiefinder.exception.ErrorMessage;
import com.marufh.cookiefinder.dto.CookieEntry;
import com.marufh.cookiefinder.exception.FileReadException;
import com.marufh.cookiefinder.validator.CommandLineInputValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvLogFileParser implements LogFileParser {

    private final CommandLineInputValidator commandLineInputValidator;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    @Override
    public Map<String, Long> parse(String file, LocalDate givenDate) {
        // Validate parser input
        commandLineInputValidator.validateParserInput(file, givenDate);

        Map<String, Long> cookieCountMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            br.readLine(); // Skip header of csv
            String line;
            while ((line = br.readLine()) != null) {

                CookieEntry cookieEntry = getCookieEntry(line);

                // Loop exit condition
                if(givenDate.isAfter(cookieEntry.getDate())) {
                    log.debug("Breaking loop. We don't need any entry after  {}", givenDate);
                    break;
                }

                // Expected data
                if(cookieEntry.getDate().isEqual(givenDate)) {
                    cookieCountMap.put(cookieEntry.getCookie(), cookieCountMap.getOrDefault(cookieEntry.getCookie(), 0L) + 1L);
                }
            }
        } catch (IOException e) {
            throw new FileReadException(ErrorMessage.FILE_READ_ERROR, file);
        }
        return cookieCountMap;
    }


    private CookieEntry getCookieEntry(String line) {
        String[] arr = line.split(",");
        return CookieEntry.builder()
                .cookie(arr[0])
                .date(LocalDate.parse(arr[1], formatter))
                .build();
    }

}
