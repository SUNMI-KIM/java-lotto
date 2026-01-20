package lotto.util.validator;

import static lotto.util.exception.ErrorMessage.DUPLICATE_BONUS_NUMBER;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class BonusNumberValidator {

    public static void validate(Lotto lotto, LottoNumber lottoNumber) {
        if (lotto.contains(lottoNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }
}
