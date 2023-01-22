package com.marufh.cookiefinder.service;

import org.springframework.boot.ApplicationArguments;

import java.util.List;

public interface MostActiveCookieService {

    List<String> findTopCountedCookie(ApplicationArguments args);
}
