package lotto.util.validator;

import static lotto.util.exception.ErrorMessage.INVALID_UNIT;

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
            throw new IllegalArgumentException(INVALID_UNIT.getMessage());
        }
    }
}
