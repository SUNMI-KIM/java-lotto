package lotto.util.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberValidatorTest {

    Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = Lotto.from(List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        ));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void 당첨번호_안에_보너스_번호가_있으면_예외를_발생한다(int input) {
        assertThrows(IllegalArgumentException.class,
                () -> BonusNumberValidator.validate(lotto, LottoNumber.from(input)));
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10})
    void 정상적인_입력이면_예외가_발생하지_않는다(int input) {
        assertDoesNotThrow(() -> BonusNumberValidator.validate(lotto, LottoNumber.from(input)));
    }
}
