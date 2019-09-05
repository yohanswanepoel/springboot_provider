package com.example.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProviderApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProviderApplication.class)
	}

	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}

}
