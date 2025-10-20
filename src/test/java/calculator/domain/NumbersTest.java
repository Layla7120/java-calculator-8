package calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumbersTest {

    @Test
    @DisplayName("올바른 숫자 문자열 배열로 Numbers 객체 생성 성공")
    void create_numbers_success() {
        assertThatCode(() -> new Numbers(new String[]{"1", "2", "3"}))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("음수가 포함된 경우 예외 발생")
    void create_numbers_with_negative() {
        assertThatThrownBy(() -> new Numbers(new String[]{"1", "-2", "3"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수 값이 포함되어 있습니다");
    }

    @Test
    @DisplayName("숫자가 아닌 문자가 포함된 경우 예외 발생")
    void create_numbers_with_non_numeric() {
        assertThatThrownBy(() -> new Numbers(new String[]{"1", "a", "3"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자가 아닌 값이 포함되어 있습니다");
    }

    @Test
    @DisplayName("빈 문자열이 포함된 경우 예외 발생")
    void create_numbers_with_blank() {
        assertThatThrownBy(() -> new Numbers(new String[]{"1", "", "2"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈 값이 포함되어 있습니다");
    }

    @Test
    @DisplayName("커스텀 구분자 사용 시 음수가 포함된 경우 예외 발생")
    void create_numbers_with_negative_and_custom_delimiter() {
        assertThatThrownBy(() -> new Numbers(new String[]{"1", "-5", "3"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수 값이 포함되어 있습니다");
    }
}
