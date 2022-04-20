package com.tomaszjanik.zadanie.startup.validators;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.tomaszjanik.zadanie.domain.Category;
import com.tomaszjanik.zadanie.exceptions.InvalidMappingException;

public class InitialDataDuplicateValuesValidator implements Consumer<List<Category>> {

    @Override
    public void accept(List<Category> categories) {
        categories.stream()
                .map(Category::getMappings)
                .forEach(this::validateMappings);
    }

    private void validateMappings(List<String> mappings) {
        var duplicatedValues = mappings.stream()
                .filter(mapping -> Collections.frequency(mappings, mapping) > 1)
                .collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(duplicatedValues)) {
            throw new InvalidMappingException("Initial Mapping list contains duplicate values in categories");
        }
    }

}