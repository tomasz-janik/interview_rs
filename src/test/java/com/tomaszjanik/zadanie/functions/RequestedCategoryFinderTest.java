package com.tomaszjanik.zadanie.functions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.exceptions.InvalidRequestException;

class RequestedCategoryFinderTest {

    private final BiFunction<List<Category>, String, Category> requestedCategoryFinder = new RequestedCategoryFinder();

    @Test
    void test() {
        //given
        var name = "XXX";
        var category = Category.builder()
                .name("YYY")
                .mappings(List.of("1", "2", "3", "4"))
                .build();
        var expectedCategory = Category.builder()
                .name(name)
                .mappings(List.of("A", "B", "C", "D"))
                .build();
        var categories = List.of(category, expectedCategory);

        //when
        var result = requestedCategoryFinder.apply(categories, name);

        //then
        assertThat(result).isEqualTo(expectedCategory);
    }

    @Test
    void testExceptionThrowing() {
        //given
        var name = "XXX";
        var category = Category.builder()
                .name("YYY")
                .mappings(List.of("1", "2", "3", "4"))
                .build();
        var expectedCategory = Category.builder()
                .name("ZZZ")
                .mappings(List.of("A", "B", "C", "D"))
                .build();
        var categories = List.of(category, expectedCategory);

        //when
        //then
        assertThrows(InvalidRequestException.class, () -> requestedCategoryFinder.apply(categories, name));
    }

}