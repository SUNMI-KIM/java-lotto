package lotto.domain;

import java.util.List;
import java.util.stream.Stream;

public class Lotto {
    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto from(List<LottoNumber> numbers) {
        return new Lotto(numbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public int countMatches(Lotto lotto) {
        return (int) this.numbers.stream()
                .filter(lotto::contains)
                .count();
    }

    public Stream<LottoNumber> stream() {
        return numbers.stream();
    }

}
