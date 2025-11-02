package lotto.domain.controller;

import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.Rank;
import lotto.domain.service.LottoService;
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
        PurchaseAmount purchaseAmount = inputHandler.readPurchaseAmount();
        Lottos lottos = lottoService.purchaseLottos(purchaseAmount);

        OutputView.printPurchasedLottos(purchaseAmount.getLottoCount(), lottos);

        Lotto lotto = inputHandler.readWinningNumbers();
        LottoNumber lottoNumber = inputHandler.readBonusNumber(lotto);

        Map<Rank, Long> countRanks = lottoService.calculateRanks(lottos, lotto, lottoNumber);
        double profit = lottoService.calculateProfitPurchaseAmount(purchaseAmount, countRanks);

        OutputView.printRank(countRanks, profit);
    }
}
