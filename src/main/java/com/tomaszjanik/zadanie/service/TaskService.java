package com.tomaszjanik.zadanie.service;

import com.tomaszjanik.zadanie.dtos.request.TaskRequestDTO;
import com.tomaszjanik.zadanie.dtos.response.TaskResponseDTO;

public interface TaskService {

    TaskResponseDTO handle(TaskRequestDTO requestDTO);

}