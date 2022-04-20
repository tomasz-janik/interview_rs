package com.tomaszjanik.zadanie.controller;

import java.util.function.Function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tomaszjanik.zadanie.dtos.request.TaskRequestDTO;
import com.tomaszjanik.zadanie.dtos.response.TaskResponseDTO;
import com.tomaszjanik.zadanie.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TaskController implements Function<TaskRequestDTO, TaskResponseDTO> {

    private final TaskService taskService;

    @Override
    @PostMapping("/divisors")
    public TaskResponseDTO apply(@RequestBody TaskRequestDTO requestDTO) {
        return taskService.handle(requestDTO);
    }

}