package com.marufh.cookiefinder.integrationtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(args = {"--f=cookie_log.csv", "--d=2018-12-09"})
public class CookieFinderApplicationTest {

    @Autowired
    ApplicationRunner runner;

    private PrintStream stdout = System.out;
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        PrintStream output = new PrintStream(byteArrayOutputStream);
        System.setOut(output);
    }

    @Test
    void testMostActivatedOneCookie() throws Exception {
        runner.run( new DefaultApplicationArguments( "--f=cookie_log.csv", "--d=2018-12-09"));
        System.out.println(byteArrayOutputStream.toString());
        assertTrue(byteArrayOutputStream.toString().contains("AtY0laUfhglK3lC7"));
    }

    @Test
    void testMostActivatedMultipleCookie() throws Exception {
        runner.run( new DefaultApplicationArguments( "--f=cookie_log.csv", "--d=2018-12-08"));
        System.out.println(byteArrayOutputStream.toString());
        assertTrue(byteArrayOutputStream.toString().contains("SAZuXPGUrfbcn5UA"));
        assertTrue(byteArrayOutputStream.toString().contains("4sMM2LxV07bPJzwf"));
        assertTrue(byteArrayOutputStream.toString().contains("fbcn5UAVanZf6UtG"));
    }

    @Test
    void testMostActivatedOneOne() throws Exception {
        runner.run( new DefaultApplicationArguments( "--f=cookie_log.csv", "--d=2018-12-07"));
        System.out.println(byteArrayOutputStream.toString());
        assertTrue(byteArrayOutputStream.toString().contains("4sMM2LxV07bPJzwf"));
    }

    @Test
    void testNothingFound() throws Exception {
        runner.run( new DefaultApplicationArguments( "--f=cookie_log.csv", "--d=2018-12-01"));
        System.out.println(byteArrayOutputStream.toString());
        assertTrue(byteArrayOutputStream.toString().contains("Nothing found!"));
    }


}
