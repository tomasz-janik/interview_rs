package com.tomaszjanik.zadanie.startup.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.exceptions.InvalidMappingException;

class InitialDataDuplicateValuesValidatorTest {

    private final Consumer<List<Category>> initialDataDuplicateValuesValidator = new InitialDataDuplicateValuesValidator();

    @Test
    void testWithoutDuplicateValues() {
        //given
        var category = List.of(Category.builder()
                        .mappings(List.of("X", "Y"))
                        .build(),
                Category.builder()
                        .mappings(List.of("X", "Y"))
                        .build());

        //when
        //then
        assertDoesNotThrow(() -> initialDataDuplicateValuesValidator.accept(category));
    }

    @Test
    void testWithDuplicateValues() {
        //given
        var category = List.of(Category.builder()
                        .mappings(List.of("X", "Y"))
                        .build(),
                Category.builder()
                        .mappings(List.of("X", "X"))
                        .build());

        //when
        //then
        assertThrows(InvalidMappingException.class, () -> initialDataDuplicateValuesValidator.accept(category));
    }

}