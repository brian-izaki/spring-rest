package com.logisticAlgaworks.logistic.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// declara que a classe é um componente de configuração de beans para o Spring
@Configuration
public class ModelMapperConfig {

    // inicializa e instância um bean para o spring
    // quando é realizado as injeções de dependencia o spring via vim aqui e gerar pra gente.
    // caso não tivesse essa classe o Spring não iria reconhecer e traria um erro dizendo que é necessário criar um bean.
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
