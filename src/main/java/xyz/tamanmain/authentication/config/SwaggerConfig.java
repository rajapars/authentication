package xyz.tamanmain.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket authenticationApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("xyz.tamanmain.authentication")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Taman Main Authentication API")
                .description("Taman Main User Authentication API Reference for Developer")
                .contact(new Contact("Raja Parsaulian B", "http://localhost:8184", "raja.parsaulian@gmail.com"))
                .license("Taman Main Authentication License").licenseUrl("raja.parsaulian@gmail.com")
                .version("1.0")
                .build();
    }

}
