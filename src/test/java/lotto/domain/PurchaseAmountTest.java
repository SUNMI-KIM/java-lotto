package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PurchaseAmountTest {

    @ParameterizedTest
    @CsvSource({
            "1000, 1",
            "5000, 5",
            "8000, 8",
            "10000, 10"
    })
    void 구매금액에_따라_로또_구매개수를_반환한다(int input, int expected) {
        PurchaseAmount purchaseAmount = PurchaseAmount.from(input);

        int actual = purchaseAmount.getLottoCount();

        assertThat(actual).isEqualTo(expected);
    }
}
