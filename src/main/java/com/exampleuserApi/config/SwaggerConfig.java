package com.exampleuserApi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;



@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket userApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.exampleuserApi"))
						.paths(regex("/api/v1/users.*"))
						.build()
						.apiInfo(metaInfo());
	}
	private ApiInfo metaInfo() {

		ApiInfo apiInfo = new ApiInfo(
				"Maintenance Project",
				"Project for training",
				"1.0",
				"Terms of Service",
				new Contact("+639065974896","jhoferson.estavillo@toolwist.com",""),
				"Tomcat License Version 5",
				"https:/www.example.com", Collections.emptyList()
				);


		return apiInfo;

	}
}



