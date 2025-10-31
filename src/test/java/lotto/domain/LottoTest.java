package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoTest {
    private Lotto lotto;

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

    @Test
    void 포함된_번호면_true를_반환한다() {
        boolean result = lotto.contains(LottoNumber.from(3));

        assertThat(result).isTrue();
    }

    @Test
    void 포함되지_않은_번호면_false를_반환한다() {
        boolean result = lotto.contains(LottoNumber.from(10));

        assertThat(result).isFalse();
    }

    @Test
    void 두_로또가_일치하는_번호가_없으면_0을_반환한다() {
        Lotto other = Lotto.from(List.of(
                LottoNumber.from(10),
                LottoNumber.from(11),
                LottoNumber.from(12),
                LottoNumber.from(13),
                LottoNumber.from(14),
                LottoNumber.from(15)
        ));

        int matchCount = lotto.countMatches(other);

        assertThat(matchCount).isZero();
    }

    @Test
    void 일부_번호가_일치하면_일치한_개수를_반환한다() {
        Lotto other = Lotto.from(List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(7),
                LottoNumber.from(8),
                LottoNumber.from(9),
                LottoNumber.from(10)
        ));

        int matchCount = lotto.countMatches(other);

        assertThat(matchCount).isEqualTo(2);
    }

    @Test
    void 모든_번호가_일치하면_6을_반환한다() {
        Lotto other = Lotto.from(List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        ));

        int matchCount = lotto.countMatches(other);

        assertThat(matchCount).isEqualTo(6);
    }

}
