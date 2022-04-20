package com.tomaszjanik.zadanie.validators;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.tomaszjanik.zadanie.dtos.request.TaskRequestDTO;
import com.tomaszjanik.zadanie.exceptions.InvalidRequestException;

public class RequestValidator implements Consumer<TaskRequestDTO> {

    @Override
    public void accept(TaskRequestDTO requestDTO) {
        validateName(requestDTO);
        validateNumbers(requestDTO);
    }

    private void validateName(TaskRequestDTO requestDTO) {
        if (requestDTO.getCategory() == null) {
            throw new InvalidRequestException("Category does not exist");
        }
    }

    private void validateNumbers(TaskRequestDTO requestDTO) {
        var invalidNumbers = requestDTO.getNumberList().stream()
                .filter(Predicate.not(number -> number > 0 && number < 21))
                .collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(invalidNumbers)) {
            throw new InvalidRequestException("Numbers out of range");
        }
    }

}