package com.tomaszjanik.zadanie.functions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.exceptions.NotEnoughMappingException;

class IntegerToWordMapperTest {

    private final BiFunction<Integer, Category, String> integerToWordMapper = new IntegerToWordMapper();

    @Test
    void test() {
        //given
        var integer = 2;
        var expectedResult = "B";

        var category = Category.builder()
                .mappings(List.of("A", expectedResult, "C", "D"))
                .build();

        //when
        var result = integerToWordMapper.apply(integer, category);

        //then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExceptionThrowing() {
        //given
        var integer = 8;
        var category = Category.builder()
                .mappings(List.of("A", "B", "C", "D"))
                .build();

        //when
        //then
        assertThrows(NotEnoughMappingException.class, () -> integerToWordMapper.apply(integer, category));
    }

}