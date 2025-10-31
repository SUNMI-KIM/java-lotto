package lotto.util.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    void 입력이_비어있거나_null이면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> InputValidator.validateNotBlank(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5",     // 숫자 개수 부족
            "1,2,3,4,5,6,7", // 숫자 개수 초과
            "1,2a,3,4,5,6",  // 숫자 아닌 문자 포함
            "1 2 3 4 5 6",   // 쉼표 누락
            "1,2,3,4,5,",    // 쉼표로 끝남
            ",1,2,3,4,5"     // 쉼표로 시작
    })
    void 당첨_번호_형식에_맞지_않으면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> InputValidator.validateWinningNumberFormat(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5,6",
            "1, 2, 3, 4, 5, 6"
    })
    void 당첨_번호_형식에_맞으면_예외가_발생하지_않는다(String input) {
        assertDoesNotThrow(() -> InputValidator.validateWinningNumberFormat(input));
    }
}
