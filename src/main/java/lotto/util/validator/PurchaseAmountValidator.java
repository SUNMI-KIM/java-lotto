package lotto.util.validator;

public class PurchaseAmountValidator extends NumberValidator {

    private static final int UNIT = 1000;
    private static final int MIN = 1000;
    private static final int MAX = 100000;

    @Override
    public void validate(String input) {
        validateNotBlank(input);
        validateNumeric(input);
        int purchaseAmount = parseAndValidateRange(input);
        validateRange(purchaseAmount, MIN, MAX);
        validateUnit(purchaseAmount);
    }

    public void validateUnit(int purchaseAmount) {
        if (purchaseAmount % UNIT != 0) {
            throw new IllegalArgumentException(/* ErrorMessage.INVALID_UNIT.getMessage() */);
        }
    }
}
