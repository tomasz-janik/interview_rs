package com.tomaszjanik.zadanie.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.domain.Mapping;
import com.tomaszjanik.zadanie.dtos.request.TaskRequestDTO;
import com.tomaszjanik.zadanie.dtos.response.MappingDTO;
import com.tomaszjanik.zadanie.dtos.response.TaskResponseDTO;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TaskServiceImplTest {

    @Mock
    private List<Category> categoryList;

    @Mock
    private Consumer<TaskRequestDTO> requestValidator;

    @Mock
    private BiFunction<List<Category>, String, Category> requestedCategoryFinder;

    @Mock
    private Function<Integer, List<Integer>> divisorFinder;

    @Mock
    private BiFunction<Integer, Category, String> integerToWordMapper;

    @Mock
    private Function<List<Mapping>, TaskResponseDTO> responseBuilder;

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskServiceImpl(categoryList, requestValidator, requestedCategoryFinder, divisorFinder,
                integerToWordMapper, responseBuilder);
    }

    @Test
    void test() {
        //given
        var categoryName = "XXX";
        var integerList = List.of(1, 2, 3);
        var request = TaskRequestDTO.builder()
                .category(categoryName)
                .numberList(integerList)
                .build();

        var category = Category.builder()
                .name(categoryName)
                .mappings(List.of("A", "B", "C"))
                .build();

        var mappings = List.of(Mapping.builder()
                        .number(1)
                        .divisors(List.of("A"))
                        .build(),
                Mapping.builder()
                        .number(2)
                        .divisors(List.of("A", "B"))
                        .build(),
                Mapping.builder()
                        .number(3)
                        .divisors(List.of("A", "C"))
                        .build());

        var response = TaskResponseDTO.builder()
                .mappingList(List.of(MappingDTO.builder()
                                .number(1)
                                .divisors(List.of("A"))
                                .build(),
                        MappingDTO.builder()
                                .number(2)
                                .divisors(List.of("A", "B"))
                                .build(),
                        MappingDTO.builder()
                                .number(3)
                                .divisors(List.of("A", "C"))
                                .build()))
                .build();

        when(requestedCategoryFinder.apply(categoryList, categoryName)).thenReturn(category);
        when(divisorFinder.apply(1)).thenReturn(List.of(1));
        when(divisorFinder.apply(2)).thenReturn(List.of(1, 2));
        when(divisorFinder.apply(3)).thenReturn(List.of(1, 3));
        when(integerToWordMapper.apply(1, category)).thenReturn("A").thenReturn("A").thenReturn("A");
        when(integerToWordMapper.apply(2, category)).thenReturn("B");
        when(integerToWordMapper.apply(3, category)).thenReturn("C");
        when(responseBuilder.apply(mappings)).thenReturn(response);

        //when
        var result = taskService.handle(request);

        //then
        assertThat(result).isEqualTo(response);
    }

}