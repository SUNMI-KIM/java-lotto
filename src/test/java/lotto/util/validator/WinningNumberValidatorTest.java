package lotto.util.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningNumberValidatorTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        validator = new WinningNumberValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 입력이_비어있거나_null이면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5",           // 5개
            "1,2,3,4,5,6,7",       // 7개
            "1,2,3,4,5,a",         // 숫자 아님
            "1, 2, 3, 4, 5 , ",    // 마지막 공백 쉼표
            "1 2 3 4 5 6"          // 쉼표 없음
    })
    void 쉼표로_구분된_6개의_숫자가_아니면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,3,4,5",
            "5,5,6,7,8,9",
            "1,2,2,2,3,4"
    })
    void 중복된_숫자가_존재하면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "999,2000,3000,4000,5000,6000",   // 999 < MIN
            "1000,2000,3000,4000,5000,100001" // 100001 > MAX
    })
    void 숫자가_범위를_벗어나면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1000,2000,3000,4000,5000,6000",
            "12345,23456,34567,45678,56789,67890"
    })
    void 올바른_형식과_범위의_입력이면_예외가_발생하지_않는다(String input) {
        assertDoesNotThrow(() -> validator.validate(input));
    }
}
