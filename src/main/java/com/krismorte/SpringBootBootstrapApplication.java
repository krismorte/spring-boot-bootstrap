package com.krismorte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.krismorte.service.CreateUserInMemoryService;

@SpringBootApplication
public class SpringBootBootstrapApplication  implements CommandLineRunner {

	@Autowired
    private CreateUserInMemoryService example;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBootstrapApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {

		example.run();
	}
	
}
