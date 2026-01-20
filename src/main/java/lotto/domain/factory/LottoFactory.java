package lotto.domain.factory;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.util.validator.LottoValidator;

public class LottoFactory {

    private final LottoValidator lottoValidator;
    private final LottoNumberFactory lottoNumberFactory;

    public LottoFactory(LottoValidator lottoValidator, LottoNumberFactory lottoNumberFactory) {
        this.lottoValidator = lottoValidator;
        this.lottoNumberFactory = lottoNumberFactory;
    }

    public Lotto create(List<Integer> numbers) {
        lottoValidator.validate(numbers);
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(lottoNumberFactory::create)
                .sorted()
                .toList();
        return Lotto.from(lottoNumbers);
    }
}
