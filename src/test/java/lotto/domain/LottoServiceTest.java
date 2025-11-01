package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.domain.factory.LottoFactory;
import lotto.domain.factory.LottoNumberFactory;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.service.LottoService;
import lotto.util.validator.LottoNumberValidator;
import lotto.util.validator.LottoValidator;
import lotto.util.validator.NumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {

    private LottoService lottoService;
    private LottoFactory lottoFactory;

    @BeforeEach
    void setUp() {
        NumberValidator numberValidator = new NumberValidator();
        LottoValidator lottoValidator = new LottoValidator(numberValidator);
        LottoNumberValidator lottoNumberValidator = new LottoNumberValidator(numberValidator);
        LottoNumberFactory lottoNumberFactory = new LottoNumberFactory(lottoNumberValidator);
        lottoFactory = new LottoFactory(lottoValidator, lottoNumberFactory);

        LottoNumberGenerator fixedGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        lottoService = new LottoService(lottoFactory, fixedGenerator);
    }

    @Test
    void 구매금액에_따라_올바른_개수의_로또를_생성한다() {
        PurchaseAmount purchaseAmount = PurchaseAmount.from(3000);

        Lottos lottos = lottoService.purchaseLottos(purchaseAmount);

        assertThat(lottos).hasSize(3);
    }

    @Test
    void 일치개수와_보너스번호에_따라_올바른_등수를_계산한다() {
        Lotto winningLotto = lottoFactory.create(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.from(7);

        Lottos purchasedLottos = Lottos.from(List.of(
                lottoFactory.create(List.of(1, 2, 3, 4, 5, 6)),
                lottoFactory.create(List.of(1, 2, 3, 4, 5, 7)),
                lottoFactory.create(List.of(1, 2, 3, 10, 20, 30))
        ));

        Map<Rank, Long> rankResults = lottoService.calculateRanks(purchasedLottos, winningLotto, bonusNumber);

        assertThat(rankResults.get(Rank.FIRST)).isEqualTo(1);
        assertThat(rankResults.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(rankResults.get(Rank.SECOND)).isEqualTo(1);
    }

    @Test
    void 총상금과_구매금액을_기반으로_수익률을_계산한다() {
        PurchaseAmount purchaseAmount = PurchaseAmount.from(8000);
        Map<Rank, Long> rankCounts = Map.of(
                Rank.FIFTH, 1L,
                Rank.MISS, 7L
        );

        double profitRate = lottoService.calculateProfitPurchaseAmount(purchaseAmount, rankCounts);

        assertThat(profitRate).isEqualTo(62.5);
    }
}
