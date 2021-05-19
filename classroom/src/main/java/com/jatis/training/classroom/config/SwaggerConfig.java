package com.jatis.training.classroom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI(@Value("${server.port:8080}") int port) {
		return new OpenAPI().addServersItem(new Server().url("http://localhost:8000"))
					.info(new Info().title("Gateway").version("V1")
						.license(new License().name("Apache 2.0")))
				.addServersItem(new Server().url("http://localhost:"+port))
					.info(new Info().title("Gateway").version("V1")
						.license(new License().name("Apache 2.0")))
				;
	}
}
