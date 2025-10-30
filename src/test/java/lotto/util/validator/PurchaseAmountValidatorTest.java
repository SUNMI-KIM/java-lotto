package lotto.util.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseAmountValidatorTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        validator = new PurchaseAmountValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 입력이_비어있거나_null이면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-100", "-9999"})
    void 음수면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "1a", ".", "+", ";"})
    void 숫자가_아니면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"9999999999", "2147483648"})
    void int_범위를_초과하면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "999"})
    void 구입금액이_1000원_미만이면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }


    @ParameterizedTest
    @ValueSource(strings = {"1100", "3001", "10001"})
    void 구입금액이_1000원_단위로_나누어떨어지지_않으면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }
}
