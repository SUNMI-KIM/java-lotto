package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    void 동일한_숫자는_같은_로또번호로_간주된다() {
        LottoNumber number1 = LottoNumber.from(10);
        LottoNumber number2 = LottoNumber.from(10);

        assertThat(number1).isEqualTo(number2);
        assertThat(number1.hashCode()).isEqualTo(number2.hashCode());
    }

    @Test
    void 다른_숫자는_서로_다른_로또번호로_간주된다() {
        LottoNumber number1 = LottoNumber.from(10);
        LottoNumber number2 = LottoNumber.from(11);

        assertThat(number1).isNotEqualTo(number2);
    }

    @Test
    void compareTo는_숫자_크기에_따라_올바르게_작동한다() {
        LottoNumber smaller = LottoNumber.from(1);
        LottoNumber bigger = LottoNumber.from(45);

        assertThat(smaller.compareTo(bigger)).isLessThan(0);
        assertThat(bigger.compareTo(smaller)).isGreaterThan(0);
    }
}
