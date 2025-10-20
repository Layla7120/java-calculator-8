package calculator.util;

import java.util.regex.Pattern;

public class StringUtils {
    private static final String DEFAULT_SEPARATOR = "[,:]";

    public static String[] split(String input) {
        if (input == null || input.isBlank()) {
            return new String[]{"0"};
        }

        String separator = DEFAULT_SEPARATOR;
        String target = input;

        String customSeparator = getCustomSeparator(input);

        if (!customSeparator.isBlank()) {
            separator = separator + "|" + Pattern.quote(customSeparator);
            int separatorIndex = input.indexOf("\\n");
            target = input.substring(separatorIndex + 2);
        }

        return target.split(separator);
    }

    private static String getCustomSeparator(String input) {
        if (input.startsWith("//")) {
            if (!input.matches("^//(.+)\\\\n.*")) {
                throw new IllegalArgumentException("구분자 형식이 올바르지 않습니다. (예: //;;\\n1;;2;;3)");
            }
            return input.replaceFirst("^//(.+)\\\\n.*", "$1");
        }
        return "";
    }
}
