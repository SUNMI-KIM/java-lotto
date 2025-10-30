package lotto.util.validator;

public abstract class Validator {

    public abstract void validate(String input);

    protected void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(/* ErrorMessage.EMPTY_INPUT.getMessage() */);
        }
    }
}
