package com.tomaszjanik.zadanie.startup;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.tomaszjanik.zadanie.domain.Category;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MappingsSupplier implements Supplier<List<Category>> {

    private final Supplier<List<Category>> initialDataReader;
    private final List<Consumer<List<Category>>> initialDataValidators;

    @Override
    public List<Category> get() {
        var categories = initialDataReader.get();

        initialDataValidators.forEach(validator -> validator.accept(categories));

        return categories;
    }

}