package lotto.util.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseAmountValidatorTest {

    NumberValidator numberValidator = new NumberValidator();
    PurchaseAmountValidator purchaseAmountValidator = new PurchaseAmountValidator(numberValidator);

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-100", "-9999"})
    void 음수면_예외가_발생한다(int input) {
        assertThrows(IllegalArgumentException.class, () -> purchaseAmountValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "999"})
    void 구입금액이_1000원_미만이면_예외가_발생한다(int input) {
        assertThrows(IllegalArgumentException.class, () -> purchaseAmountValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"100001", "1000000", "10000000"})
    void 구입금액이_100000원_초과이면_예외가_발생한다(int input) {
        assertThrows(IllegalArgumentException.class, () -> purchaseAmountValidator.validate(input));
    }


    @ParameterizedTest
    @ValueSource(ints = {1001, 2200, 3010, 4110, 10001, 100100})
    void 구입금액이_1000원_단위로_나누어떨어지지_않으면_예외가_발생한다(int input) {
        assertThrows(IllegalArgumentException.class, () -> purchaseAmountValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000, 4000, 10000, 100000})
    void 정상적인_입력이면_예외가_발생하지_않는다(int input) {
        assertDoesNotThrow(() -> purchaseAmountValidator.validate(input));
    }
}
