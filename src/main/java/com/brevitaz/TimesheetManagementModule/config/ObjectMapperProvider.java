package com.brevitaz.TimesheetManagementModule.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dhvanan on 28/2/18 Wednesday
 * @project TimesheetManagementModule
 **/
@Configuration
public class ObjectMapperProvider {


    private static ObjectMapper mapper;

    public ObjectMapperProvider()
    {
    }

    @Bean
    public ObjectMapper getInstance()
    {
        if (mapper == null) {
            mapper = new ObjectMapper();
            return mapper;
        }
        return mapper;

    }

}
