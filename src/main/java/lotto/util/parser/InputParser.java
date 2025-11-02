package lotto.util.parser;

import static lotto.util.exception.ErrorMessage.INVALID_NUMBER_FORMAT;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    private static final String DELIMITER = ",";

    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT.getMessage());
        }
    }

    public static List<Integer> parseToIntegerList(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(InputParser::parseToInt)
                .toList();
    }
}
