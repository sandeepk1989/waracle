package com.waracle.cakemgr;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.dto.CakeDto;
import com.waracle.cakemgr.services.CakeService;

/**
 * Cake Manager Spring Boot Application Starter
 * @author sandeepk1989
 *
 */
@SpringBootApplication
public class CakeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CakeManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CakeService cakeService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<CakeDto>> typeReference = new TypeReference<List<CakeDto>>() {
			};

			InputStream inputStream = TypeReference.class.getResourceAsStream("/test-data.json");
			try {
				List<CakeDto> cakes = mapper.readValue(inputStream, typeReference);
				cakeService.saveAll(cakes);
				System.out.println("cakes Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}
}
