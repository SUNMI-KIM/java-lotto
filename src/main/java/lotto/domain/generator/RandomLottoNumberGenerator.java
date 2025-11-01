package lotto.domain.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    public static final int MIN = 1;
    public static final int MAX = 45;
    public static final int LOTTO_SIZE = 6;

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(MIN, MAX, LOTTO_SIZE);
    }
}
