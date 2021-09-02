package com.shiva.ebay.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
			title = "eBay - Home Assignment",
			description = "eBay - Home Assignment",
			version = "1.0",
			license = @License(name = "eBay 2.0", url = "https://www.ebay.com")
		)
)
public class EbayAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbayAssessmentApplication.class, args);
	}

}
