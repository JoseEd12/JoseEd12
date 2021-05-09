package com.depaseo;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableSwagger2
@Configuration
public class MvcConfig {

    private static final String BASIC_AUTH = "basicAuth";
    private static final String BEARER_AUTH = "Bearer";
    
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error_403").setViewName("error_403");
	}


 
	/*
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

*/
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(usersApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.depaseo.controller.api"))
				.paths(PathSelectors.any())
				.build();
				// .securitySchemes(securitySchemes()).securityContexts(List.of(securityContext()));
	}
	
	 private ApiInfo usersApiInfo() {
	        return new ApiInfoBuilder()
	                .title("Servicio de reserva de ticket")
	                .version("1.0")
	                .license("Apache License Version 2.0")
	                .build();
	    }
	 /*
	 private ApiKey apiKey() {
		    return new ApiKey("token", "Authorization", "header");
		}
	 
	/*
	   private List<SecurityScheme> securitySchemes() {
	        return List.of( new ApiKey("Bearer", "Authorization", "header"));
	    }
	 
	    private SecurityContext securityContext() {
	        return SecurityContext.builder().securityReferences(List.of( bearerAuthReference())).build();
	    }

	 
	    private SecurityReference bearerAuthReference() {
	        return new SecurityReference(BEARER_AUTH, new AuthorizationScope[0]);
	    }
	    */
}
