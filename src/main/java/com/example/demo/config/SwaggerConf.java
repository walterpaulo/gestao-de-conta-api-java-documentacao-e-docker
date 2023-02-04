package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConf {

    public @Bean OpenAPI noteAPI() {
        Contact contato = new Contact();
        contato.setEmail("walter0paulo@hotmail.com");
        contato.setName("Walter Paulo");
        contato.setUrl("https://www.walterpaulo.com.br");
        
        return new OpenAPI()
			.info(
				new Info()
					.title("Conta API")
                    .contact(contato)
					.description("API de gestão de conta")
					.version("0.0.1-SNAPSHOT")
					.license(
						new License().name("MIT").url("https://opensource.org/licenses/MIT")
					)
			);
	  }
}
