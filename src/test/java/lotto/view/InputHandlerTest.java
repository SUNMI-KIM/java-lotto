package lotto.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.domain.Lotto;
import lotto.domain.factory.LottoFactory;
import lotto.domain.factory.LottoNumberFactory;
import lotto.domain.factory.PurchaseAmountFactory;
import lotto.util.validator.LottoNumberValidator;
import lotto.util.validator.LottoValidator;
import lotto.util.validator.NumberValidator;
import lotto.util.validator.PurchaseAmountValidator;
import lotto.view.handler.InputHandler;
import org.junit.jupiter.api.Test;

class InputHandlerTest extends NsTest {

    @Override
    public void runMain() {
        NumberValidator numberValidator = new NumberValidator();

        LottoValidator lottoValidator = new LottoValidator(numberValidator);
        LottoNumberValidator lottoNumberValidator = new LottoNumberValidator(numberValidator);
        PurchaseAmountValidator purchaseAmountValidator = new PurchaseAmountValidator(numberValidator);

        LottoNumberFactory lottoNumberFactory = new LottoNumberFactory(lottoNumberValidator);
        LottoFactory lottoFactory = new LottoFactory(lottoValidator, lottoNumberFactory);
        PurchaseAmountFactory purchaseAmountFactory = new PurchaseAmountFactory(purchaseAmountValidator);

        InputHandler inputHandler = new InputHandler(
                purchaseAmountFactory,
                lottoFactory,
                lottoNumberFactory
        );

        inputHandler.readPurchaseAmount();
        Lotto lotto = inputHandler.readWinningNumbers();
        inputHandler.readBonusNumber(lotto);
    }

    @Test
    void 전체_입력_정상_흐름() {
        run("8000", "1,2,3,4,5,6", "7");

        assertThat(output()).contains(
                "구입 금액을 입력해주세요.",
                "당첨 번호를 입력해 주세요.",
                "보너스 번호를 입력해 주세요."
        );
    }

    @Test
    void 구입금액_입력_예외_흐름() {
        assertSimpleTest(() -> {
            run("abc", "8000", "1,2,3,4,5,6", "7");

            assertThat(output()).contains(
                    "구입 금액을 입력해주세요.",
                    "구입 금액을 입력해주세요.",
                    "당첨 번호를 입력해 주세요.",
                    "보너스 번호를 입력해 주세요."
            );
        });
    }

    @Test
    void 당첨번호_입력_예외_흐름() {
        assertSimpleTest(() -> {
            run("8000", "1,2,a,4,5,6", "1,2,3,4,5,6", "7");

            assertThat(output()).contains(
                    "구입 금액을 입력해주세요.",
                    "당첨 번호를 입력해 주세요.",
                    "당첨 번호를 입력해 주세요.",
                    "보너스 번호를 입력해 주세요."
            );
        });
    }

    @Test
    void 보너스번호_입력_예외_흐름() {
        assertSimpleTest(() -> {
            run("8000", "1,2,3,4,5,6", "abc", "7");

            assertThat(output()).contains(
                    "보너스 번호를 입력해 주세요.",
                    "보너스 번호를 입력해 주세요."
            );
        });
    }

    @Test
    void 보너스번호_입력_중복_예외_흐름() {
        assertSimpleTest(() -> {
            run("8000", "1,2,3,4,5,6", "5", "7");

            assertThat(output()).contains(
                    "보너스 번호를 입력해 주세요.",
                    "보너스 번호를 입력해 주세요."
            );
        });
    }
}
