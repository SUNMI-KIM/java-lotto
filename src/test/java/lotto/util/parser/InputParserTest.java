package lotto.util.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"0", "123", "  42  ", "9999"})
    void 문자열이_정수로_변환_가능하면_int_값으로_반환한다(String input) {
        int result = InputParser.parseToInt(input);
        assertThat(result).isEqualTo(Integer.parseInt(input.trim()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "1a", ".", "+", "-", " "})
    void 문자열이_정수가_아니면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseToInt(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3", " 4 , 5 , 6 ", "10,20,30"})
    void 쉼표로_구분된_숫자_문자열을_정수_리스트로_변환한다(String input) {
        List<Integer> result = InputParser.parseToIntegerList(input);
        List<Integer> expected = List.of(input.replaceAll("\\s+", "").split(","))
                .stream()
                .map(Integer::parseInt)
                .toList();
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,a,3", "a,b,c"})
    void 쉼표로_구분된_문자열_중_하나라도_숫자가_아니면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseToIntegerList(input));
    }
}
