package com.marufh.cookiefinder.extractor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MostActiveCookieExtractor implements CookieExtractor {

    @Override
    public List<String> extract(Map<String, Long> cookieMap) {
        if(cookieMap.isEmpty()) {
            return List.of("Nothing found!");
        }

        Long max = Collections.max(cookieMap.values());
        log.info("Max count: {}", max);

        return cookieMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(max))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
