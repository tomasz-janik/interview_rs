package com.tomaszjanik.zadanie.startup.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.exceptions.InvalidMappingException;

class InitialDataDuplicateEntriesValidatorTest {

    private final Consumer<List<Category>> initialDataDuplicateEntriesValidator = new InitialDataDuplicateEntriesValidator();

    @Test
    void testWithoutDuplicateEntries() {
        //given
        var category = List.of(Category.builder()
                        .name("XXX")
                        .build(),
                Category.builder()
                        .name("YYY")
                        .build());

        //when
        //then
        assertDoesNotThrow(() -> initialDataDuplicateEntriesValidator.accept(category));
    }

    @Test
    void testWithDuplicateEntries() {
        //given
        var category = List.of(Category.builder()
                        .name("XXX")
                        .build(),
                Category.builder()
                        .name("XXX")
                        .build());

        //when
        //then
        assertThrows(InvalidMappingException.class, () -> initialDataDuplicateEntriesValidator.accept(category));
    }

}