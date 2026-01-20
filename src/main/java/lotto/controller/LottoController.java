package lotto.controller;

import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.Rank;
import lotto.service.LottoService;
import lotto.view.OutputView;
import lotto.view.handler.InputHandler;

public class LottoController {
    private final LottoService lottoService;
    private final InputHandler inputHandler;

    public LottoController(LottoService lottoService, InputHandler inputHandler) {
        this.lottoService = lottoService;
        this.inputHandler = inputHandler;
    }

    public void run() {
        handleLottoPurchase();
    }

    private void handleLottoPurchase() {

        PurchaseAmount purchaseAmount = inputHandler.readPurchaseAmount();
        Lottos purchasedLottos = lottoService.purchaseLottos(purchaseAmount);

        OutputView.printPurchasedLottos(purchaseAmount.getLottoCount(), purchasedLottos);

        handleWinningResult(purchaseAmount, purchasedLottos);
    }

    private void handleWinningResult(PurchaseAmount purchaseAmount, Lottos purchasedLottos) {

        Lotto winningLotto = inputHandler.readWinningNumbers();
        LottoNumber bonusNumber = inputHandler.readBonusNumber(winningLotto);

        Map<Rank, Long> rankCounts = lottoService.calculateRanks(purchasedLottos, winningLotto, bonusNumber);
        double profitRate = lottoService.calculateProfitPurchaseAmount(purchaseAmount, rankCounts);

        OutputView.printRank(rankCounts, profitRate);
    }
}
