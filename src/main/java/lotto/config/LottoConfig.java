package lotto.config;

import lotto.domain.factory.LottoFactory;
import lotto.domain.factory.LottoNumberFactory;
import lotto.domain.factory.PurchaseAmountFactory;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.generator.RandomLottoNumberGenerator;
import lotto.domain.service.LottoService;
import lotto.util.validator.LottoNumberValidator;
import lotto.util.validator.LottoValidator;
import lotto.util.validator.NumberValidator;
import lotto.util.validator.PurchaseAmountValidator;
import lotto.view.handler.InputHandler;

public class LottoConfig {

    public InputHandler inputHandler() {
        return new InputHandler(purchaseAmountFactory(), lottoFactory(), lottoNumberFactory());
    }

    public LottoService lottoService() {
        return new LottoService(lottoFactory(), lottoNumberGenerator());
    }

    public LottoNumberGenerator lottoNumberGenerator() {
        return new RandomLottoNumberGenerator();
    }

    public LottoFactory lottoFactory() {
        return new LottoFactory(lottoValidator(), lottoNumberFactory());
    }

    public LottoNumberFactory lottoNumberFactory() {
        return new LottoNumberFactory(lottoNumberValidator());
    }

    public LottoValidator lottoValidator() {
        return new LottoValidator(numberValidator());
    }

    public LottoNumberValidator lottoNumberValidator() {
        return new LottoNumberValidator(numberValidator());
    }

    public PurchaseAmountFactory purchaseAmountFactory() {
        return new PurchaseAmountFactory(purchaseAmountValidator());
    }

    public PurchaseAmountValidator purchaseAmountValidator() {
        return new PurchaseAmountValidator(numberValidator());
    }

    private NumberValidator numberValidator() {
        return new NumberValidator();
    }
}
