package lotto.util.validator;

public abstract class Validator {

    protected Validator() {
        // 외부 인스턴스화 방지
    }

    public void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(/* ErrorMessage.EMPTY_INPUT.getMessage() */);
        }
    }
}
