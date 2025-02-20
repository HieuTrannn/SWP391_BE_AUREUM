package com.example.SkincareProductSales;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "AUREUM-API", version = "2.0", description = "API"))
public class SkincareProductSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkincareProductSalesApplication.class, args);
	}

}
