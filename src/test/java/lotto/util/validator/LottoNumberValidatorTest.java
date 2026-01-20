package lotto.util.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberValidatorTest {

    NumberValidator numberValidator = new NumberValidator();
    LottoNumberValidator lottoNumberValidator = new LottoNumberValidator(numberValidator);

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3})
    void 음수면_예외가_발생한다(int input) {
        assertThrows(IllegalArgumentException.class, () -> lottoNumberValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 로또_범위를_초과하면_예외가_발생한다(int input) {
        assertThrows(IllegalArgumentException.class, () -> lottoNumberValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 44, 45})
    void 정상적인_입력이면_예외가_발생하지_않는다(int input) {
        assertDoesNotThrow(() -> lottoNumberValidator.validate(input));
    }
}
