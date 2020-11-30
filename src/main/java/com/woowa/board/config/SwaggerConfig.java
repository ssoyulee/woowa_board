package com.woowa.board.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    
	private ApiInfo apiInfo() {

		return new ApiInfoBuilder()
				.title("Board")
				.version("1.0")
				.description("Woowa Bros Borad API")
				.build();
	}

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build();
//	}
	
	@Bean
	public Docket boardApi() {
		
	    List<ResponseMessage> responseMessages = new ArrayList<>();
	    responseMessages.add(new ResponseMessageBuilder()
	            .code(200)
	            .message("OK")
	            .build());
	    responseMessages.add(new ResponseMessageBuilder()
	            .code(500)
	            .message("Internal Server Error")
	            .build());
	    
		return new Docket(DocumentationType.SWAGGER_2)
//                .consumes(getConsumeContentTypes())
//                .produces(getProduceContentTypes())	
				.useDefaultResponseMessages(false)
				.groupName("com.woowa")
				.apiInfo(this.apiInfo())
				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.woowa.board.board"))
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/**/api/**"))
				.build()
				.globalResponseMessage(RequestMethod.GET, responseMessages)
				.globalResponseMessage(RequestMethod.POST, responseMessages)
				.globalResponseMessage(RequestMethod.PUT, responseMessages)
				.globalResponseMessage(RequestMethod.DELETE, responseMessages);
		
	}

}