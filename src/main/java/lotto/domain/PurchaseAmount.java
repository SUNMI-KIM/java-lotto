package lotto.domain;

public class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;
    private final int purchaseAmount;

    private PurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public static PurchaseAmount from(int purchaseAmount) {
        return new PurchaseAmount(purchaseAmount);
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public int getLottoCount() {
        return purchaseAmount / LOTTO_PRICE;
    }
}
