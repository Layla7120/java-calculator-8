package calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("숫자 리스트의 합을 정확하게 계산")
    void sum_numbers() {
        int result = calculator.sum(Arrays.asList(1, 2, 3));
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("빈 리스트의 합은 0")
    void sum_empty_list() {
        int result = calculator.sum(Collections.emptyList());
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("단일 숫자 리스트의 합 계산")
    void sum_single_number() {
        int result = calculator.sum(Collections.singletonList(7));
        assertThat(result).isEqualTo(7);
    }
}
