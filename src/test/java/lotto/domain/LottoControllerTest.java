package lotto.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.config.LottoConfig;
import lotto.domain.controller.LottoController;
import org.junit.jupiter.api.Test;

public class LottoControllerTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @Override
    public void runMain() {
        LottoConfig config = new LottoConfig();
        LottoController lottoController = config.lottoController();
        lottoController.run();
    }

    @Test
    void 로또_정상_흐름_테스트() {
        run("8000", "1,2,3,4,5,6", "7");

        assertThat(output()).contains(
                "구입 금액을 입력해주세요.",
                "8개를 구매했습니다.",
                "당첨 번호를 입력해 주세요.",
                "보너스 번호를 입력해 주세요.",
                "당첨 통계",
                "총 수익률은"
        );
    }

    @Test
    void 구입금액_입력_예외_테스트() {
        assertSimpleTest(() -> {
            run("abc", "8000", "1,2,3,4,5,6", "7");

            assertThat(output()).contains(
                    "구입 금액을 입력해주세요.",
                    ERROR_MESSAGE,
                    "구입 금액을 입력해주세요.",
                    "8개를 구매했습니다.",
                    "당첨 번호를 입력해 주세요.",
                    "보너스 번호를 입력해 주세요.",
                    "당첨 통계",
                    "총 수익률은"
            );
        });
    }

    @Test
    void 당첨번호_입력_예외_테스트() {
        assertSimpleTest(() -> {
            run("8000", "1,2,a,4,5,6", "1,2,3,4,5,6", "7");

            assertThat(output()).contains(
                    "구입 금액을 입력해주세요.",
                    "당첨 번호를 입력해 주세요.",
                    ERROR_MESSAGE,
                    "당첨 번호를 입력해 주세요.",
                    "보너스 번호를 입력해 주세요.",
                    "당첨 통계",
                    "총 수익률은"
            );
        });
    }

    @Test
    void 보너스번호_입력_예외_테스트() {
        assertSimpleTest(() -> {
            run("8000", "1,2,3,4,5,6", "abc", "7");

            assertThat(output()).contains(
                    "보너스 번호를 입력해 주세요.",
                    ERROR_MESSAGE,
                    "보너스 번호를 입력해 주세요.",
                    "당첨 통계",
                    "총 수익률은"
            );
        });
    }
}
