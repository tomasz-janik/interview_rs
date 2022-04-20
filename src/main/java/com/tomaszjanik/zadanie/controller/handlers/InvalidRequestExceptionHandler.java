package com.tomaszjanik.zadanie.controller.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tomaszjanik.zadanie.controller.TaskController;
import com.tomaszjanik.zadanie.dtos.response.ErrorResponseDTO;
import com.tomaszjanik.zadanie.exceptions.InvalidRequestException;

@ControllerAdvice(assignableTypes = TaskController.class)
public class InvalidRequestExceptionHandler {

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handle(InvalidRequestException exception) {
        return new ResponseEntity<>(buildErrorResponse(exception), HttpStatus.BAD_REQUEST);
    }

    private ErrorResponseDTO buildErrorResponse(InvalidRequestException exception) {
        return ErrorResponseDTO.builder()
                .message(exception.getMessage())
                .build();
    }

}