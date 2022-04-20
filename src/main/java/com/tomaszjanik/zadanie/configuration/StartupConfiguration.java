package com.tomaszjanik.zadanie.configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.startup.InitialDataReader;
import com.tomaszjanik.zadanie.startup.MappingsSupplier;
import com.tomaszjanik.zadanie.startup.validators.InitialDataDuplicateEntriesValidator;
import com.tomaszjanik.zadanie.startup.validators.InitialDataDuplicateValuesValidator;

@Configuration
public class StartupConfiguration {

    @Bean
    public Supplier<List<Category>> mappingsSupplier(Supplier<List<Category>> initialDataReader,
            List<Consumer<List<Category>>> initialDataValidators) {
        return new MappingsSupplier(initialDataReader, initialDataValidators);
    }

    @Bean
    public Supplier<List<Category>> initialDataReader(ObjectMapper objectMapper) {
        return new InitialDataReader(objectMapper);
    }

    @Bean
    public List<Consumer<List<Category>>> initialDataValidators() {
        return List.of(new InitialDataDuplicateEntriesValidator(), new InitialDataDuplicateValuesValidator());
    }

}
