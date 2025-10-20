package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String input = Console.readLine();

        if(input.isBlank()) {
            System.out.println("결과 : 0");
            return;
        }

        String target = input;

        String separator = "[,:]";

        String custom_separator = getCustomSeparator(input);

        if(!custom_separator.isBlank()) {
            separator = separator + "|" + Pattern.quote(custom_separator);

            int separatorIndex = input.indexOf("\\n");
            target = input.substring(separatorIndex + 2);

        }

        String[] numbers = target.split(separator);

        validate(numbers);

        int answer = calculate(numbers);

        System.out.println("결과 : " + answer);
    }

    private static void validate(String[] numbers) {
        for(String number : numbers) {
            if(number.isBlank()) {
                throw new IllegalArgumentException("빈 값이 포함되어 있습니다.");
            }

            if(!number.matches("-?\\d+")) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + number);
            }

            int num = Integer.parseInt(number);
            if(num <= 0) {
                throw new IllegalArgumentException("음수 값이 포함되어 있습니다: " + number);
            }
        }
    }

    private static int calculate(String[] numbers) {
        int total = 0;
        for(String number : numbers) {
            int numberInt = Integer.parseInt(number);
            total += numberInt;
        }
        return total;
    }

    private static String getCustomSeparator(String input) {
        if(!input.startsWith("//")){
            return "";
        }

        int endIndex = input.indexOf("\\n");

        if(endIndex <= 2){
            return "";
        }

        return input.substring(2, endIndex);
    }
}
