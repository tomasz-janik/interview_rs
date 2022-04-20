package com.tomaszjanik.zadanie.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tomaszjanik.zadanie.dtos.request.TaskRequestDTO;
import com.tomaszjanik.zadanie.dtos.response.MappingDTO;
import com.tomaszjanik.zadanie.dtos.response.TaskResponseDTO;
import com.tomaszjanik.zadanie.service.TaskService;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    private Function<TaskRequestDTO, TaskResponseDTO> taskController;

    @BeforeEach
    void setUp() {
        taskController = new TaskController(taskService);
    }

    @Test
    void test() {
        //given
        var request = TaskRequestDTO.builder()
                .category("Category")
                .numberList(List.of(1, 2, 3))
                .build();
        var response = TaskResponseDTO.builder()
                .mappingList(List.of(MappingDTO.builder()
                        .number(1)
                        .divisors(List.of("A"))
                        .build()))
                .build();

        when(taskService.handle(request)).thenReturn(response);

        //when
        var result = taskController.apply(request);

        //then
        assertThat(result).isEqualTo(result);
    }

}