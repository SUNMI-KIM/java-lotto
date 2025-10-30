package lotto.util.validator;

public abstract class NumberValidator extends Validator{
    private static final String NUMBER_REGEX = "\\d+";

    protected void validateNumeric(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException(/* ErrorMessage.INVALID_NUMBER.getMessage() */);
        }
    }

    protected int parseAndValidateRange(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(/* ErrorMessage.INVALID_RANGE.getMessage() */);
        }
    }

    protected void validateRange(int number, int min, int max) {
        if (number < min || number > max) {
            throw new IllegalArgumentException(/* ErrorMessage.INVALID_RANGE.getMessage() */);
        }
    }
}
