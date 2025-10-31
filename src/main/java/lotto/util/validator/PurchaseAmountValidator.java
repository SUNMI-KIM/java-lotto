package lotto.util.validator;

public class PurchaseAmountValidator {

    private static final int UNIT = 1000;
    private static final int MIN = 1000;
    private static final int MAX = 100000;

    private final NumberValidator numberValidator;

    public PurchaseAmountValidator(NumberValidator numberValidator) {
        this.numberValidator = numberValidator;
    }

    public void validate(Integer purchaseAmount) {
        numberValidator.validateRange(purchaseAmount, MIN, MAX);
        validateUnit(purchaseAmount);
    }

    public void validateUnit(int purchaseAmount) {
        if (purchaseAmount % UNIT != 0) {
            throw new IllegalArgumentException(/* ErrorMessage.INVALID_UNIT.getMessage() */);
        }
    }
}
