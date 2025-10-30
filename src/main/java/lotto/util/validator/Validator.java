package lotto.util.validator;

public abstract class Validator {
    protected void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(/* ErrorMessage.EMPTY_INPUT.getMessage() */);
        }
    }
}
