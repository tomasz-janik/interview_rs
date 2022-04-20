package com.tomaszjanik.zadanie.configuration;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.domain.Mapping;
import com.tomaszjanik.zadanie.dtos.request.TaskRequestDTO;
import com.tomaszjanik.zadanie.dtos.response.TaskResponseDTO;
import com.tomaszjanik.zadanie.functions.DivisorFinder;
import com.tomaszjanik.zadanie.functions.IntegerToWordMapper;
import com.tomaszjanik.zadanie.functions.RequestedCategoryFinder;
import com.tomaszjanik.zadanie.functions.ResponseBuilder;
import com.tomaszjanik.zadanie.service.TaskService;
import com.tomaszjanik.zadanie.service.TaskServiceImpl;
import com.tomaszjanik.zadanie.validators.RequestValidator;

@Configuration
public class TaskConfiguration {

    @Bean
    public TaskService taskService(Supplier<List<Category>> mappingsSupplier,
            Consumer<TaskRequestDTO> requestValidator,
            BiFunction<List<Category>, String, Category> existingCategoryValidator,
            Function<Integer, List<Integer>> divisorFinder,
            BiFunction<Integer, Category, String> integerToWordMapper,
            Function<List<Mapping>, TaskResponseDTO> responseBuilder) {
        return new TaskServiceImpl(mappingsSupplier.get(), requestValidator, existingCategoryValidator, divisorFinder,
                integerToWordMapper, responseBuilder);
    }

    @Bean
    public Consumer<TaskRequestDTO> requestValidator() {
        return new RequestValidator();
    }

    @Bean
    public BiFunction<List<Category>, String, Category> existingCategoryValidator() {
        return new RequestedCategoryFinder();
    }

    @Bean
    public Function<Integer, List<Integer>> divisorFinder() {
        return new DivisorFinder();
    }

    @Bean
    public BiFunction<Integer, Category, String> integerToWordMapper() {
        return new IntegerToWordMapper();
    }

    @Bean
    public Function<List<Mapping>, TaskResponseDTO> responseBuilder() {
        return new ResponseBuilder();
    }

}