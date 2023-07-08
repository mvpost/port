package ru.mtsbank.port;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PortApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortApplication.class, args);
	}

}
