package lotto.util.validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumberValidator extends NumberValidator{

    private static final int MIN = 1000;
    private static final int MAX = 100000;

    @Override
    public void validate(String input) {
        validateNotBlank(input);
        validateFormat(input);
        List<Integer> numbers = parseToNumbers(input);
        validateDuplicate(numbers);
        validateRangeForAll(numbers);
    }

    private void validateFormat(String input) {
        if (!input.matches("\\s*\\d+(\\s*,\\s*\\d+){5}\\s*")) {
            throw new IllegalArgumentException(/* INVALID_WINNING_NUMBER_FORMAT.getMessage() */);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != numbers.size()) {
            throw new IllegalArgumentException(/* DUPLICATE_WINNING_NUMBER.getMessage() */);
        }
    }

    private void validateRangeForAll(List<Integer> numbers) {
        for (int number : numbers) {
            validateRange(number, MIN, MAX);
        }
    }

    private List<Integer> parseToNumbers(String input) {
        String[] tokens = input.split(",");
        List<Integer> numbers = new ArrayList<>();

        for (String token : tokens) {
            String trimmed = token.trim();
            validateNumeric(trimmed);
            numbers.add(Integer.parseInt(trimmed));
        }
        return numbers;
    }
}
