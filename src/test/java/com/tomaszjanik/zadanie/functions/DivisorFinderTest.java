package com.tomaszjanik.zadanie.functions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DivisorFinderTest {

    private final Function<Integer, List<Integer>> divisorFinder = new DivisorFinder();

    @MethodSource("testCases")
    @ParameterizedTest
    void test(Integer integer, List<Integer> expectedDivisors) {
        //given
        //when
        var result = divisorFinder.apply(integer);

        //then
        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedDivisors);
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(0, List.of()),
                Arguments.of(1, List.of(1)),
                Arguments.of(2, List.of(1, 2)),
                Arguments.of(4, List.of(1, 2, 4))
        );
    }

}