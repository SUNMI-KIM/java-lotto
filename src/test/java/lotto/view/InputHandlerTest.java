package lotto.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.config.LottoConfig;
import lotto.view.handler.InputHandler;
import org.junit.jupiter.api.Test;

class InputHandlerTest extends NsTest {

    @Override
    public void runMain() {
        LottoConfig config = new LottoConfig();
        InputHandler inputHandler = config.inputHandler();

        inputHandler.readPurchaseAmount();
        inputHandler.readWinningNumbers();
        inputHandler.readBonusNumber();
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
}
