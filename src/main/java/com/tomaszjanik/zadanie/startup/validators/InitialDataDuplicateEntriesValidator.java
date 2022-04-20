package com.tomaszjanik.zadanie.startup.validators;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.exceptions.InvalidMappingException;

public class InitialDataDuplicateEntriesValidator implements Consumer<List<Category>> {

    @Override
    public void accept(List<Category> categories) {
        var duplicatedEntries = categories.stream()
                .filter(category -> Collections.frequency(categories, category) > 1)
                .map(Category::getName)
                .collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(duplicatedEntries)) {
            throw new InvalidMappingException("Initial Mapping list contains duplicate entries");
        }
    }

}