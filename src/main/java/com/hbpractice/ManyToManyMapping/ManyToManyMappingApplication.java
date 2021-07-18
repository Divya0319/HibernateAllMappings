package com.hbpractice.ManyToManyMapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManyToManyMappingApplication {
	
//	@PostConstruct
//	public void init() {
	// Setting Spring Boot SetTimeZone to Indian Standard Time
//    TimeZone.setDefault(TimeZone.getTimeZone("IST"));
//	}

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyMappingApplication.class, args);
	}

}
