package lotto.view;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {

    private static final String PURCHASED_MESSAGE = "개를 구매했습니다.";
    private static final String STATISTICS_HEADER = "당첨 통계";
    private static final String SEPARATOR_LINE = "---";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.%n";
    private static final String BONUS_BALL_MATCH_MESSAGE = ", 보너스 볼 일치";

    private static final String RANK_RESULT_FORMAT = "%d개 일치%s (%s원) - %d개%n";
    private static final String LOTTO_NUMBER_FORMAT = "[%s]";
    private static final String LOTTO_NUMBER_DELIMITER = ", ";

    private static final String EMPTY_LINE = "";

    public static void printPurchasedLottos(int lottosCount, Lottos lottos) {
        System.out.println();
        System.out.println(lottosCount + PURCHASED_MESSAGE);

        lottos.stream()
                .map(lotto -> lotto.stream()
                        .map(n -> String.valueOf(n.getLottoNumber()))
                        .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER)))
                .map(numbers -> String.format(LOTTO_NUMBER_FORMAT, numbers))
                .forEach(System.out::println);
    }

    public static void printRank(Map<Rank, Long> rankCounts, double profitRate) {
        System.out.println(EMPTY_LINE);
        System.out.println(STATISTICS_HEADER);
        System.out.println(SEPARATOR_LINE);

        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .forEach(rank -> System.out.printf(
                        RANK_RESULT_FORMAT,
                        rank.getMatchCount(),
                        formatMatchBonus(rank.isMatchBonus()),
                        String.format("%,d", rank.getPrize()),
                        rankCounts.getOrDefault(rank, 0L)
                ));

        System.out.printf(PROFIT_RATE_MESSAGE, profitRate);
    }

    private static String formatMatchBonus(boolean isMatchBonus) {
        if (isMatchBonus) {
            return BONUS_BALL_MATCH_MESSAGE;
        }
        return EMPTY_LINE;
    }
}
