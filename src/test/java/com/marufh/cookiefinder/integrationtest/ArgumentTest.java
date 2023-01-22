package com.marufh.cookiefinder.integrationtest;

import com.marufh.cookiefinder.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(args = {"--f=cookie_log.csv", "--d=2018-12-09"})
@ExtendWith(SpringExtension.class)
public class ArgumentTest {

    @Autowired
    ApplicationRunner runner;

    @Test
    void noException() {
        assertDoesNotThrow(() -> runner.run( new DefaultApplicationArguments( "--f=cookie_log.csv", "--d=2018-12-09")));
    }

    @Test
    void testMissingFileArgument() {
        InvalidArgumentException thrown = assertThrows(InvalidArgumentException.class, () ->
                runner.run( new DefaultApplicationArguments( "--d=2018-12-09"))
        );

        assertEquals("File argument not found, please check for the argument f", thrown.getMessage());
    }

    @Test
    void testIllegalFileExtensionArgument() {
        InvalidArgumentException thrown = assertThrows(InvalidArgumentException.class, () ->
                runner.run( new DefaultApplicationArguments( "--f=cookie_log.mp3", "--d=2018-12-09"))
        );
        assertEquals("'mp3' is not valid file extension", thrown.getMessage());
    }

    @Test
    void testMissingDateArgument() {
        InvalidArgumentException thrown =  assertThrows(InvalidArgumentException.class, () ->
                runner.run( new DefaultApplicationArguments( "--f=cookie_log.csv"))
        );
        assertEquals("Date argument not found,  please check for the argument d", thrown.getMessage());
    }

    @Test
    void testIllegalDateArgument() {
        InvalidArgumentException thrown = assertThrows(InvalidArgumentException.class, () ->
            runner.run( new DefaultApplicationArguments( "--f=cookie_log.cvs", "--d=2018-09"))
        );
        assertEquals("'2018-09' is not a valid date", thrown.getMessage());
    }
}
