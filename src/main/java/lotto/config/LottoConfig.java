package lotto.config;

import lotto.domain.controller.LottoController;
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

    public LottoController lottoController() {
        return new LottoController(lottoService(), inputHandler());
    }

    private InputHandler inputHandler() {
        return new InputHandler(purchaseAmountFactory(), lottoFactory(), lottoNumberFactory());
    }

    private LottoService lottoService() {
        return new LottoService(lottoFactory(), lottoNumberGenerator());
    }

    private LottoNumberGenerator lottoNumberGenerator() {
        return new RandomLottoNumberGenerator();
    }

    private LottoFactory lottoFactory() {
        return new LottoFactory(lottoValidator(), lottoNumberFactory());
    }

    private LottoNumberFactory lottoNumberFactory() {
        return new LottoNumberFactory(lottoNumberValidator());
    }

    private LottoValidator lottoValidator() {
        return new LottoValidator(numberValidator());
    }

    private LottoNumberValidator lottoNumberValidator() {
        return new LottoNumberValidator(numberValidator());
    }

    private PurchaseAmountFactory purchaseAmountFactory() {
        return new PurchaseAmountFactory(purchaseAmountValidator());
    }

    private PurchaseAmountValidator purchaseAmountValidator() {
        return new PurchaseAmountValidator(numberValidator());
    }

    private NumberValidator numberValidator() {
        return new NumberValidator();
    }
}
