package com.webapp.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.webapp.boot.models.service.FileServiceInterface;

@SpringBootApplication
public class SpringBootDatajpaApplication implements CommandLineRunner {

	@Autowired
	FileServiceInterface fileService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDatajpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileService.deleteAll();
		fileService.init();
		
	}

}
