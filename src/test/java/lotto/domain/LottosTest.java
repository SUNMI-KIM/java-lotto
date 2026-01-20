package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private Lottos lottos;
    private Lotto lotto;
    private LottoNumber lottoNumber;

    @BeforeEach
    void setUp() {
        Lotto lotto1 = Lotto.from(List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        ));

        Lotto lotto2 = Lotto.from(List.of(
                LottoNumber.from(1),
                LottoNumber.from(3),
                LottoNumber.from(5),
                LottoNumber.from(7),
                LottoNumber.from(9),
                LottoNumber.from(11)
        ));

        Lotto lotto3 = Lotto.from(List.of(
                LottoNumber.from(10),
                LottoNumber.from(20),
                LottoNumber.from(30),
                LottoNumber.from(40),
                LottoNumber.from(41),
                LottoNumber.from(42)
        ));

        lottos = Lottos.from(List.of(lotto1, lotto2, lotto3));

        lotto = Lotto.from(List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        ));

        lottoNumber = LottoNumber.from(6);
    }

    @Test
    void 각_Lotto의_일치_개수를_리스트로_반환한다() {
        List<Integer> matchCounts = lottos.countAllMatches(lotto);

        assertThat(matchCounts).containsExactly(6, 3, 0);
    }

    @Test
    void 각_Lotto의_보너스_번호_일치_개수를_리스트로_반환한다() {
        List<Boolean> matchCounts = lottos.countAllNumberMatches(lottoNumber);

        assertThat(matchCounts).containsExactly(true, false, false);
    }
}
