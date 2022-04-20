package com.tomaszjanik.zadanie.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DivisorFinder implements Function<Integer, List<Integer>> {

    @Override
    public List<Integer> apply(Integer integer) {
        var divisors = new ArrayList<Integer>();

        for (int counter = 1; counter <= Math.sqrt(integer); counter++) {
            if (integer % counter == 0) {
                if (integer / counter == counter) {
                    divisors.add(counter);
                } else {
                    divisors.add(counter);
                    divisors.add(integer / counter);
                }
            }
        }

        return divisors;
    }

}