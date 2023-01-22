package com.marufh.cookiefinder;

import com.marufh.cookiefinder.service.MostActiveCookieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class CookieFinderApplication implements ApplicationRunner {

	private final MostActiveCookieService mostActiveCookieService;

	public static void main(String[] args) {
		SpringApplication.run(CookieFinderApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		long startTime = System.currentTimeMillis();
		mostActiveCookieService.findTopCountedCookie(args)
				.forEach(System.out::println);
		long endTime = System.currentTimeMillis();
		log.info("Total time taken: {}", (endTime-startTime));
	}

}
