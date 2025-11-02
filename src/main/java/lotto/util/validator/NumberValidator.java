package lotto.util.validator;

import lotto.util.exception.ErrorMessage;

public class NumberValidator {

    protected void validateRange(int number, int min, int max) {
        if (number < min || number > max) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE.getMessage());
        }
    }
}
