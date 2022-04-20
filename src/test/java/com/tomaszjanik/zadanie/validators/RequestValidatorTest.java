package com.tomaszjanik.zadanie.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import com.tomaszjanik.zadanie.dtos.request.TaskRequestDTO;
import com.tomaszjanik.zadanie.exceptions.InvalidRequestException;

class RequestValidatorTest {

    private final Consumer<TaskRequestDTO> requestValidator = new RequestValidator();

    @Test
    void test() {
        //given
        var request = TaskRequestDTO.builder()
                .category("XXX")
                .numberList(List.of(1, 2, 3))
                .build();

        //when
        //then
        assertDoesNotThrow(() -> requestValidator.accept(request));
    }

    @Test
    void testRequestWithInvalidCategory() {
        //given
        var request = TaskRequestDTO.builder()
                .numberList(List.of(1, 2, 22))
                .build();

        //when
        //then
        assertThrows(InvalidRequestException.class, () -> requestValidator.accept(request));
    }

    @Test
    void testRequestWithInvalidNumbers() {
        //given
        var request = TaskRequestDTO.builder()
                .category("XXX")
                .numberList(List.of(1, 2, 22))
                .build();

        //when
        //then
        assertThrows(InvalidRequestException.class, () -> requestValidator.accept(request));
    }

}