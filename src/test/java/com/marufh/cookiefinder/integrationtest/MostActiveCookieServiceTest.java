package com.marufh.cookiefinder.integrationtest;

import com.marufh.cookiefinder.service.MostActiveCookieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(args = {"--f=cookie_log.csv", "--d=2018-12-09"})
@ExtendWith(SpringExtension.class)
public class MostActiveCookieServiceTest {

    @Autowired
    private MostActiveCookieService mostActiveCookieService;

    @Test
    void testFindTopCountedCookie() {
        List<String> topCookieList =  mostActiveCookieService.findTopCountedCookie(new DefaultApplicationArguments( "--f=cookie_log.csv", "--d=2018-12-09"));
        assertEquals(1, topCookieList.size());
        assertEquals(List.of("AtY0laUfhglK3lC7"), topCookieList);


        topCookieList =  mostActiveCookieService.findTopCountedCookie(new DefaultApplicationArguments( "--f=cookie_log.csv", "--d=2018-12-08"));
        assertEquals(3, topCookieList.size());
        Collections.sort(topCookieList);
        assertEquals(List.of("4sMM2LxV07bPJzwf", "SAZuXPGUrfbcn5UA",  "fbcn5UAVanZf6UtG"), topCookieList);

        topCookieList =  mostActiveCookieService.findTopCountedCookie(new DefaultApplicationArguments( "--f=cookie_log.csv", "--d=2018-12-07"));
        assertEquals(1, topCookieList.size());
        assertEquals(List.of("4sMM2LxV07bPJzwf"), topCookieList);
    }

    @Test
    void testFindNoCookie() {
        List<String> topCookieList =  mostActiveCookieService.findTopCountedCookie(new DefaultApplicationArguments( "--f=cookie_log.csv", "--d=2018-12-02"));
        assertEquals(1, topCookieList.size());
        assertEquals(List.of("Nothing found!"), topCookieList);

    }
}
