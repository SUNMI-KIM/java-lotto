package lotto.util.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoValidator {

    private static final int MIN = 1;
    private static final int MAX = 45;

    private final NumberValidator numberValidator;

    public LottoValidator(NumberValidator numberValidator) {
        this.numberValidator = numberValidator;
    }

    public void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRangeForAll(numbers);
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != numbers.size()) {
            throw new IllegalArgumentException(/* DUPLICATE_WINNING_NUMBER.getMessage() */);
        }
    }

    private void validateRangeForAll(List<Integer> numbers) {
        for (int number : numbers) {
            numberValidator.validateRange(number, MIN, MAX);
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(/* INVALID_WINNING_NUMBER_SIZE.getMessage() */);
        }
    }
}
