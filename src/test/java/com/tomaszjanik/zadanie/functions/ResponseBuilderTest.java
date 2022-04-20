package com.tomaszjanik.zadanie.functions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.tomaszjanik.zadanie.domain.Mapping;
import com.tomaszjanik.zadanie.dtos.response.MappingDTO;
import com.tomaszjanik.zadanie.dtos.response.TaskResponseDTO;

class ResponseBuilderTest {

    private final Function<List<Mapping>, TaskResponseDTO> responseBuilder = new ResponseBuilder();

    @Test
    void test() {
        //given
        var number = 1;
        var divisors = List.of("A", "B", "C");
        var mapping = Mapping.builder()
                .number(number)
                .divisors(divisors)
                .build();
        var expectedResult = TaskResponseDTO.builder()
                .mappingList(List.of(MappingDTO.builder()
                        .number(number)
                        .divisors(divisors)
                        .build()))
                .build();

        //when
        var result = responseBuilder.apply(List.of(mapping));

        //then
        assertThat(result).isEqualTo(expectedResult);
    }

}