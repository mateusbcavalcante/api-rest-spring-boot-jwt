package com.movie.api.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
						  .select()
						  .apis(RequestHandlerSelectors.basePackage("com.movieapp.controllers"))
						  .paths(buildPaths())
						  .build();
	}
	
	private Predicate<String> buildPaths() {

		List<Predicate<String>> basePaths = new ArrayList<>();
		basePaths.add(PathSelectors.ant("/auth"));
		basePaths.add(PathSelectors.ant("/api/v1/**"));

        return Predicates.or(basePaths);
    }
}
