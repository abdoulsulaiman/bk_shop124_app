package com.bk.shop4.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;



@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("SpringShop API")
	              .description("Shop24 WebService Application")
	              .version("v0.0.1")
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation()
	              .description("Shop24 Documentation")
	              .url("http://localhost:8000/v3/api-docs"));
	  }
}
