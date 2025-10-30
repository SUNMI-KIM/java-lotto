package lotto.util.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberValidatorTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        validator = new BonusNumberValidator();
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
    @ValueSource(strings = {"0", "46"})
    void 로또_범위를_초과하면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "15", "45"})
    void 정상적인_입력이면_예외가_발생하지_않는다(String input) {
        assertDoesNotThrow(() -> validator.validate(input));
    }
}
