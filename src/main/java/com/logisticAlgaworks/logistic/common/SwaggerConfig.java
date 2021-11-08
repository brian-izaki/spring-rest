package com.logisticAlgaworks.logistic.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // habilita o swagger no Spring Boot
public class SwaggerConfig {

    // docket permite configurar os aspectos dos endpoints
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // com apis e paths que o swagger reconhece os endpoints da aplicação
                .apis(RequestHandlerSelectors.any()) // digo que as apis estão disponíveis
                .paths(PathSelectors.any()) // digo que todos os caminhos estão disponíveis
                .build();
    }

}
