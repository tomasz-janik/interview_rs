package com.tomaszjanik.zadanie.controller.handlers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.tomaszjanik.zadanie.dtos.response.ErrorResponseDTO;
import com.tomaszjanik.zadanie.exceptions.InvalidRequestException;

class InvalidRequestExceptionHandlerTest {

    private final InvalidRequestExceptionHandler invalidRequestExceptionHandler = new InvalidRequestExceptionHandler();

    @Test
    void test() {
        //given
        var errorMessage = "Error_Message";
        var exception = new InvalidRequestException(errorMessage);
        var expectedResponseBody = ErrorResponseDTO.builder()
                .message(errorMessage)
                .build();

        //when
        var result = invalidRequestExceptionHandler.handle(exception);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody()).isEqualTo(expectedResponseBody);
    }

}