package com.bbm.khodar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KhodarEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhodarEventApplication.class, args);
	}

}
