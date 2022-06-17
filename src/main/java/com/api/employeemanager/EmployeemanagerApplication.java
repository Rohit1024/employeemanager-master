package com.api.employeemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@PropertySource("classpath:ValidationMessages.properties")
public class EmployeemanagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeemanagerApplication.class, args);
	}
}
