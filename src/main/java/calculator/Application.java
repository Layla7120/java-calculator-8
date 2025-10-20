package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String input = Console.readLine();
        String target = input;

        String separator = "[,:]";

        String custom_separator = getCustomSeparator(input);

        if(!custom_separator.isBlank()) {
            separator = separator + "|" + Pattern.quote(custom_separator);

            int separatorIndex = input.indexOf("\\n");
            target = input.substring(separatorIndex + 2);

        }

        String[] numbers = target.split(separator);

        int answer = calculate(numbers);

        System.out.println("결과 : " + answer);
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

        System.out.println("입력 문자열 길이: " + input.length());
        System.out.println("인덱스 3의 문자: " + input.charAt(3));

        if(endIndex <= 2){
            return "";
        }

        return input.substring(2, endIndex);
    }
}
