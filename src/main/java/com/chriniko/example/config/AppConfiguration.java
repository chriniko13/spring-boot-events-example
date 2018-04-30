package com.chriniko.example.config;

import com.chriniko.example.domain.Ticket;
import com.chriniko.example.dto.PlayTicketDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration

@EnableAutoConfiguration

@ComponentScan("com.chriniko.example")

public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<PlayTicketDto, Ticket>() {
            @Override
            protected void configure() {
                map(source.getNumbers(), destination.getNumbers().getChoice());
            }
        });

        return modelMapper;
    }

}
