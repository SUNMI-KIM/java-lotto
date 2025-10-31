package lotto.util.validator;

public class InputValidator {

    private static final String WINNING_NUMBER_REGEX = "(\\s*\\d+\\s*,\\s*){5}\\s*\\d+\\s*";

    public static void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(/* ErrorMessage.EMPTY_INPUT.getMessage() */);
        }
    }

    public static void validateWinningNumberFormat(String input) {
        if (!input.matches(WINNING_NUMBER_REGEX)) {
            throw new IllegalArgumentException(/* INVALID_WINNING_NUMBER_FORMAT.getMessage() */);
        }
    }
}
