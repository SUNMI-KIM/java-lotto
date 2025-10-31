package lotto.util.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoValidatorTest {

    NumberValidator numberValidator = new NumberValidator();
    LottoValidator lottoValidator = new LottoValidator(numberValidator);

    @Test
    void 중복된_숫자가_존재하면_예외가_발생한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        assertThrows(IllegalArgumentException.class, () -> lottoValidator.validate(numbers));
    }

    @Test
    void 숫자_중_하나라도_범위를_벗어나면_예외가_발생한다() {
        List<Integer> numbers = List.of(-1, 1, 2, 3, 4, 5);

        assertThrows(IllegalArgumentException.class, () -> lottoValidator.validate(numbers));
    }

    @ParameterizedTest
    @MethodSource("inputProvider")
    void 리스트의_인자_개수가_6개가_아니면_예외가_발생한다(List<Integer> numbers) {
        assertThrows(IllegalArgumentException.class, () -> lottoValidator.validate(numbers));
    }

    @Test
    void 모든_숫자가_범위_내이고_중복이_없으면_예외가_발생하지_않는다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        assertDoesNotThrow(() -> lottoValidator.validate(numbers));
    }

    @Test
    void 빈_리스트가_주어지면_예외가_발생한다() {
        List<Integer> numbers = List.of();

        assertThrows(IllegalArgumentException.class, () -> lottoValidator.validate(numbers));
    }

    private static Stream<List<Integer>> inputProvider() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5, 6, 7),
                List.of(1, 2, 3, 4, 5)
        );
    }
}
