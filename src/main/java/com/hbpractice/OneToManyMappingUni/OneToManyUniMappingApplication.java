package com.hbpractice.OneToManyMappingUni;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneToManyUniMappingApplication {
	
	@PostConstruct
	public void init() {
	// Setting Spring Boot SetTimeZone to Indian Standard Time
    TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	}

	public static void main(String[] args) {
		SpringApplication.run(OneToManyUniMappingApplication.class, args);
	}

}
