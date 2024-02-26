package com.helloworld.paginacion_servicios_rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") 
				.allowedOrigins("localhost:4200")
				.allowedMethods("GET", "POST", "PUT", "DELETE") 
				.allowedHeaders("*");
	}
}