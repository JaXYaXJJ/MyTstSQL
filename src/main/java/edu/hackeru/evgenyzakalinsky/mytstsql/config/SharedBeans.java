package edu.hackeru.evgenyzakalinsky.mytstsql.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharedBeans {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}