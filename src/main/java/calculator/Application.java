package calculator;

import calculator.domain.Calculator;
import calculator.domain.Numbers;
import calculator.util.StringUtils;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        String input = InputView.readInput();

        String[] numberStrings = StringUtils.split(input);

        Numbers numbers = new Numbers(numberStrings);

        Calculator calculator = new Calculator();
        int result = calculator.sum(numbers.getNumbers());

        OutputView.printResult(result);
    }
}