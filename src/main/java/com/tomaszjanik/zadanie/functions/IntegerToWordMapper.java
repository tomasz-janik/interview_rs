package com.tomaszjanik.zadanie.functions;

import java.util.function.BiFunction;

import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.exceptions.NotEnoughMappingException;

public class IntegerToWordMapper implements BiFunction<Integer, Category, String> {

    @Override
    public String apply(Integer integer, Category category) {
        if (category.getMappings().size() < integer) {
            throw new NotEnoughMappingException("Category does not have enough mapping for computed divisors");
        }

        return category.getMappings().get(integer - 1);
    }

}