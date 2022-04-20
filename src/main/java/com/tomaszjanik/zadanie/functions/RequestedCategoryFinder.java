package com.tomaszjanik.zadanie.functions;

import java.util.List;
import java.util.function.BiFunction;

import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.exceptions.InvalidRequestException;

public class RequestedCategoryFinder implements BiFunction<List<Category>, String, Category> {

    @Override
    public Category apply(List<Category> categoryList, String requestedCategoryName) {
        return categoryList.stream()
                .filter(categoryName -> categoryName.getName().equals(requestedCategoryName))
                .findFirst()
                .orElseThrow(() -> new InvalidRequestException("Category does not exist"));
    }

}