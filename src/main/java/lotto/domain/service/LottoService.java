package lotto.domain.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.Rank;
import lotto.domain.factory.LottoFactory;
import lotto.domain.generator.LottoNumberGenerator;

public class LottoService {
    private static final int BONUS_MATCH_CONDITION = 5;

    private final LottoFactory lottoFactory;
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoService(LottoFactory lottoFactory, LottoNumberGenerator lottoNumberGenerator) {
        this.lottoFactory = lottoFactory;
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lottos purchaseLottos(PurchaseAmount purchaseAmount) {
        int lottoCount = purchaseAmount.getLottoCount();

        List<Lotto> lottos = IntStream.range(0, lottoCount)
                .mapToObj(i -> lottoFactory.create(lottoNumberGenerator.generate()))
                .toList();

        return Lottos.from(lottos);
    }

    public Map<Rank, Long> calculateRanks(Lottos purchasedLottos, Lotto winningLotto, LottoNumber bonusNumber) {
        List<Integer> matchCounts = purchasedLottos.countAllMatches(winningLotto);
        List<Boolean> bonusMatches = purchasedLottos.countAllNumberMatches(bonusNumber);

        return IntStream.range(0, matchCounts.size())
                .mapToObj(i -> {
                    int matchCount = matchCounts.get(i);
                    boolean bonusMatch = (matchCount == BONUS_MATCH_CONDITION) && bonusMatches.get(i);
                    return Rank.from(matchCount, bonusMatch);
                })
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));
    }

    public double calculateProfitPurchaseAmount(PurchaseAmount purchaseAmount, Map<Rank, Long> rankCounts) {
        int totalPrize = rankCounts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue().intValue())
                .sum();

        return (double) totalPrize / purchaseAmount.getPurchaseAmount() * 100;
    }
}
