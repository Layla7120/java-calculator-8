package calculator.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringUtilsTest {

    @Test
    @DisplayName("빈 문자열 또는 null 입력 시 0을 포함하는 배열 반환")
    void split_empty_or_null() {
        assertThat(StringUtils.split("")).isEqualTo(new String[]{"0"});
        assertThat(StringUtils.split(null)).isEqualTo(new String[]{"0"});
    }

    @Test
    @DisplayName("단일 숫자 입력 시 해당 숫자를 포함하는 배열 반환")
    void split_single_number() {
        assertThat(StringUtils.split("7")).isEqualTo(new String[]{"7"});
    }

    @Test
    @DisplayName("기본 구분자(쉼표)로 분리")
    void split_default_delimiter_comma() {
        assertThat(StringUtils.split("1,2")).isEqualTo(new String[]{"1", "2"});
    }

    @Test
    @DisplayName("기본 구분자(콜론)로 분리")
    void split_default_delimiter_colon() {
        assertThat(StringUtils.split("5:6")).isEqualTo(new String[]{"5", "6"});
    }

    @Test
    @DisplayName("기본 구분자(복합)로 분리")
    void split_default_delimiters_mixed() {
        assertThat(StringUtils.split("1,2:3")).isEqualTo(new String[]{"1", "2", "3"});
    }

    @Test
    @DisplayName("커스텀 구분자로 분리")
    void split_custom_delimiter() {
        assertThat(StringUtils.split("//;\\n1;2;3")).isEqualTo(new String[]{"1", "2", "3"});
    }

    @Test
    @DisplayName("커스텀 구분자(특수 문자)로 분리")
    void split_custom_delimiter_special_char() {
        assertThat(StringUtils.split("//@\\n4@5@6")).isEqualTo(new String[]{"4", "5", "6"});
    }

    @Test
    @DisplayName("커스텀 구분자 패턴이 올바르지 않으면 예외 발생")
    void split_invalid_custom_delimiter_pattern() {
        assertThatThrownBy(() -> StringUtils.split("//;1;2;3")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("커스텀 구분자와 기본 구분자 함께 사용")
    void split_custom_and_default_delimiters() {
        assertThat(StringUtils.split("//;\\n1,2;3")).isEqualTo(new String[]{"1", "2", "3"});
    }
}
