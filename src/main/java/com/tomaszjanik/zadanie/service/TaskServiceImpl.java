package com.tomaszjanik.zadanie.service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.domain.Mapping;
import com.tomaszjanik.zadanie.dtos.request.TaskRequestDTO;
import com.tomaszjanik.zadanie.dtos.response.TaskResponseDTO;

import lombok.RequiredArgsConstructor;

/**
 * This class started to become huge and in my mind it's responsible for too much stuff. I would refactor it
 * by e.g. moving handle method (the private one) to another class.
 */
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final List<Category> categoryList;

    private final Consumer<TaskRequestDTO> requestValidator;
    private final BiFunction<List<Category>, String, Category> requestedCategoryFinder;
    private final Function<Integer, List<Integer>> divisorFinder;
    private final BiFunction<Integer, Category, String> integerToWordMapper;
    private final Function<List<Mapping>, TaskResponseDTO> responseBuilder;

    @Override
    public TaskResponseDTO handle(TaskRequestDTO request) {
        requestValidator.accept(request);

        var requestedCategory = requestedCategoryFinder.apply(categoryList, request.getCategory());

        var divisors = request.getNumberList().stream()
                .map(number -> handle(number, requestedCategory))
                .collect(Collectors.toList());

        return responseBuilder.apply(divisors);
    }

    private Mapping handle(Integer number, Category requestedCategory) {
        var divisors = divisorFinder.apply(number);

        var mappedDivisors = divisors.stream()
                .map(divisor -> integerToWordMapper.apply(divisor, requestedCategory))
                .collect(Collectors.toList());

        return Mapping.builder()
                .number(number)
                .divisors(mappedDivisors)
                .build();
    }

}