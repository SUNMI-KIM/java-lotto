package lotto.util.validator;

public class PurchaseAmountValidator extends Validator {

    private static final String NUMBER_REGEX = "\\d+";

    private static final int UNIT = 1000;

    @Override
    public void validate(String input) {
        validateNotBlank(input);
        validateNumeric(input);
        int purchaseAmount = parseAndValidateRange(input);
        validateRange(purchaseAmount);
        validateUnit(purchaseAmount);
    }

    public void validateNumeric(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException(/* ErrorMessage.INVALID_NUMBER.getMessage() */);
        }
    }

    public int parseAndValidateRange(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(/* ErrorMessage.INVALID_RANGE.getMessage() */);
        }
    }

    public void validateRange(int purchaseAmount) {
        if (purchaseAmount < UNIT) {
            throw new IllegalArgumentException(/* ErrorMessage.INVALID_RANGE.getMessage() */);
        }
    }

    public void validateUnit(int purchaseAmount) {
        if (purchaseAmount % UNIT != 0) {
            throw new IllegalArgumentException(/* ErrorMessage.INVALID_UNIT.getMessage() */);
        }
    }
}
