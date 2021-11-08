package com.logisticAlgaworks.logistic.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
                //.apis(RequestHandlerSelectors.any()) // digo que as apis estão disponíveis
                .apis(RequestHandlerSelectors.basePackage("com.logisticAlgaworks.logistic.api.controller")) // digo para trazer apenas os endpoints da aplicação (default ele tbm traz os do spring)
                .paths(PathSelectors.any()) // digo que todos os caminhos estão disponíveis
                .build()
                // add info para o layout da documentação do swagger.
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API simples de Logisticas")
                .description("Esta API foi desenvolvida durante o mergulho Spring REST da AlgaWorks, é uma API com a " +
                        "finalidade de gerenciar a logistica de entregas para seus clientes")
                .contact(new Contact("Brian Izaki", "https://br.linkedin.com/public-profile/in/brian-izaki-45b60b186", "brian.izaki@gmail.com"))
                .build();
    }

}
