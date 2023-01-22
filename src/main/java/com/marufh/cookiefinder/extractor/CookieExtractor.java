package com.marufh.cookiefinder.extractor;

import java.util.List;
import java.util.Map;

public interface CookieExtractor {
    List<String> extract(Map<String, Long> cookieMap);
}
