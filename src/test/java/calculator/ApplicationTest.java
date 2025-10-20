package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 빈_문자열_입력() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 단일_문자열_입력() {
        assertSimpleTest(() -> {
            run("7");
            assertThat(output()).contains("결과 : 7");
        });
    }

    @Test
    void 쉼표_구분자() {
        assertSimpleTest(() -> {
            run("1,2");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    void 콜론_구분자() {
        assertSimpleTest(() -> {
            run("5:6");
            assertThat(output()).contains("결과 : 11");
        });
    }

    @Test
    void 쉼표_콜론_혼합_구분자() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_단일_문자() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_특수_문자() {
        assertSimpleTest(() -> {
            run("//@\\n4@5@6");
            assertThat(output()).contains("결과 : 15");
        });
    }

    @Test
    void 커스텀_구분자와_기본_구분자_혼용() {
        assertSimpleTest(() -> {
            run("//*\\n1*2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_길이_2이상() {
        assertSimpleTest(() -> {
            run("//^^\\n1^^2^^3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 예외_음수_포함() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,-2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_음수_포함_커스텀() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;-5;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_숫자_아닌_값_포함() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_숫자_대신_빈_문자열() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_마지막_구분자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,2,:"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외__커스텀_구분자_패턴만_존재() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//:\n"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_잘못된_커스텀_구분자_패턴() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
