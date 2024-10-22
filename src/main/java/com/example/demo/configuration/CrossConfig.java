package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossConfig implements WebMvcConfigurer {

	// Add CORS configuration here
	public void addCorMapping(CorsRegistry registry) {

		registry.addMapping("/**")
				.allowedOrigins("http://127.0.0.1:5500")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}

}




