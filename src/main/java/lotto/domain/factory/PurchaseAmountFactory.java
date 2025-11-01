package lotto.domain.factory;

import lotto.domain.PurchaseAmount;
import lotto.util.validator.PurchaseAmountValidator;

public class PurchaseAmountFactory {

    private final PurchaseAmountValidator purchaseAmountValidator;

    public PurchaseAmountFactory(PurchaseAmountValidator purchaseAmountValidator) {
        this.purchaseAmountValidator = purchaseAmountValidator;
    }

    public PurchaseAmount create(int purchaseAmount) {
        purchaseAmountValidator.validate(purchaseAmount);
        return PurchaseAmount.from(purchaseAmount);
    }
}
