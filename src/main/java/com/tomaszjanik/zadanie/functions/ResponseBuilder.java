package com.tomaszjanik.zadanie.functions;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.tomaszjanik.zadanie.domain.Mapping;
import com.tomaszjanik.zadanie.dtos.response.MappingDTO;
import com.tomaszjanik.zadanie.dtos.response.TaskResponseDTO;

public class ResponseBuilder implements Function<List<Mapping>, TaskResponseDTO> {

    @Override
    public TaskResponseDTO apply(List<Mapping> mappings) {
        return TaskResponseDTO.builder()
                .mappingList(map(mappings))
                .build();
    }

    private List<MappingDTO> map(List<Mapping> mappings) {
        return mappings.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private MappingDTO map(Mapping mapping) {
        return MappingDTO.builder()
                .number(mapping.getNumber())
                .divisors(mapping.getDivisors())
                .build();
    }

}