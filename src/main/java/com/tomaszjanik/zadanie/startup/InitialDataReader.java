package com.tomaszjanik.zadanie.startup;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomaszjanik.zadanie.ZadanieApplication;
import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.exceptions.InvalidMappingException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InitialDataReader implements Supplier<List<Category>> {

    private final ObjectMapper objectMapper;

    @Override
    public List<Category> get() {
        var classPathResource = new ClassPathResource("/mappings.json", ZadanieApplication.class);

        try (InputStream inputStream = classPathResource.getInputStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (IOException exception) {
            throw new InvalidMappingException(exception);
        }
    }

}