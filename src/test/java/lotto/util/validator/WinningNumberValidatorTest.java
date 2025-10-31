package lotto.util.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

public class WinningNumberValidatorTest {

    NumberValidator numberValidator = new NumberValidator();
    WinningNumberValidator winningNumberValidator = new WinningNumberValidator(numberValidator);

    @Test
    void 중복된_숫자가_존재하면_예외가_발생한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        assertThrows(IllegalArgumentException.class, () -> winningNumberValidator.validate(numbers));
    }

    @Test
    void 숫자_중_하나라도_범위를_벗어나면_예외가_발생한다() {
        List<Integer> numbers = List.of(-1, 1, 2, 3, 4, 5);

        assertThrows(IllegalArgumentException.class, () -> winningNumberValidator.validate(numbers));
    }

    @Test
    void 모든_숫자가_범위_내이고_중복이_없으면_예외가_발생하지_않는다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        assertDoesNotThrow(() -> winningNumberValidator.validate(numbers));
    }

    @Test
    void 빈_리스트가_주어지면_예외가_발생하지_않는다() {
        List<Integer> numbers = List.of();

        assertDoesNotThrow(() -> winningNumberValidator.validate(numbers));
    }
}
