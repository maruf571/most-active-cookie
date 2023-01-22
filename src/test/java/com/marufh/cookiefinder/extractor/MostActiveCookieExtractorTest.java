package com.marufh.cookiefinder.extractor;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MostActiveCookieExtractorTest {

    @Test
    void findSingleTopActiveCookie() {
        Map<String, Long> cookieMap = new HashMap<>();
        cookieMap.put("A", 1L);
        cookieMap.put("B", 2L);
        cookieMap.put("C", 3L);
        cookieMap.put("D", 4L);
        cookieMap.put("E", 5L);

        CookieExtractor cookieExtractor = new MostActiveCookieExtractor();
        assertEquals(List.of("E"), cookieExtractor.extract(cookieMap));
    }

    @Test
    void findMultipleTopActiveCookie() {
        Map<String, Long> cookieMap = new HashMap<>();
        cookieMap.put("A", 1L);
        cookieMap.put("B", 2L);
        cookieMap.put("C", 5L);
        cookieMap.put("D", 5L);
        cookieMap.put("E", 5L);

        CookieExtractor cookieExtractor = new MostActiveCookieExtractor();
        List<String> topActivatedCookies = cookieExtractor.extract(cookieMap);
        Collections.sort(topActivatedCookies);
        assertEquals(List.of("C", "D", "E"), topActivatedCookies);
    }
}
