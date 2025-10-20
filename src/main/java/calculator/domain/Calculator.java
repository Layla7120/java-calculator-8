package calculator.domain;

import java.util.List;

public class Calculator {
    public int sum(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }
}
