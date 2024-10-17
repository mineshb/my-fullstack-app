package com.mineshb.handson;

import com.mineshb.handson.config.PaginationProperties;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
		info = @Info(
				title = "Users microservice REST API Documentation",
				description = "Users microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Minesh Bhatiya",
						email = "minesh.tbd@xyz.com",
						url = "https://minbha.orgfree.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://localhost:8080"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "User microservice REST API Documentation",
				url = "https://localhost:8080/swagger-ui.html"
		)
)
public class HandsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandsonApplication.class, args);
	}

}
