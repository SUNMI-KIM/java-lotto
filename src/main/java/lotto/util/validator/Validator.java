package lotto.util.validator;

public abstract class Validator {

    public void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(/* ErrorMessage.EMPTY_INPUT.getMessage() */);
        }
    }
}
