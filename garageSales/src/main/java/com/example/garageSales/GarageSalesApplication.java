package com.example.garageSales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication()
@ComponentScan(basePackages = "com.example.garageSales.Controller") // Replace with your base package
public class GarageSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarageSalesApplication.class, args);
	}
}
