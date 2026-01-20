package lotto.domain.factory;

import lotto.domain.LottoNumber;
import lotto.util.validator.LottoNumberValidator;

public class LottoNumberFactory {

    private final LottoNumberValidator lottoNumberValidator;

    public LottoNumberFactory(LottoNumberValidator lottoNumberValidator) {
        this.lottoNumberValidator = lottoNumberValidator;
    }

    public LottoNumber create(int lottoNumber) {
        lottoNumberValidator.validate(lottoNumber);
        return LottoNumber.from(lottoNumber);
    }
}
