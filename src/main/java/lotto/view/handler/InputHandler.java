package lotto.view.handler;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.PurchaseAmount;
import lotto.domain.factory.LottoFactory;
import lotto.domain.factory.LottoNumberFactory;
import lotto.domain.factory.PurchaseAmountFactory;
import lotto.util.parser.InputParser;
import lotto.view.InputView;


public class InputHandler {

    private final PurchaseAmountFactory purchaseAmountFactory;
    private final LottoFactory lottoFactory;
    private final LottoNumberFactory lottoNumberFactory;

    public InputHandler(PurchaseAmountFactory purchaseAmountFactory,
                        LottoFactory lottoFactory,
                        LottoNumberFactory lottoNumberFactory) {
        this.purchaseAmountFactory = purchaseAmountFactory;
        this.lottoFactory = lottoFactory;
        this.lottoNumberFactory = lottoNumberFactory;
    }

    public PurchaseAmount readPurchaseAmount() {
        while (true) {
            try {
                String input = InputView.inputPurchaseAmount();
                int amount = InputParser.parseToInt(input);
                return purchaseAmountFactory.create(amount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Lotto readWinningNumbers() {
        while (true) {
            try {
                String input = InputView.inputWinningNumbers();
                List<Integer> numbers = InputParser.parseToIntegerList(input);
                return lottoFactory.create(numbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public LottoNumber readBonusNumber() {
        while (true) {
            try {
                String input = InputView.inputBonusNumber();
                int number = InputParser.parseToInt(input);
                return lottoNumberFactory.create(number);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
