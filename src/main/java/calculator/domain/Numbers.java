package calculator.domain;

import java.util.ArrayList;
import java.util.List;

public class Numbers {
    private final List<Integer> numbers;

    public Numbers(String[] numberStrings) {
        this.numbers = new ArrayList<>();
        validateAndParse(numberStrings);
    }

    private void validateAndParse(String[] numberStrings) {
        for (String numberStr : numberStrings) {
            if (numberStr.isBlank()) {
                throw new IllegalArgumentException("빈 값이 포함되어 있습니다.");
            }

            if (!numberStr.matches("-?\\d+")) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + numberStr);
            }

            int num = Integer.parseInt(numberStr);
            if (num <= 0) {
                throw new IllegalArgumentException("음수 값이 포함되어 있습니다: " + numberStr);
            }
            numbers.add(num);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
