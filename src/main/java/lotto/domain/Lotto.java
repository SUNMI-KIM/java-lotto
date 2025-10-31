package lotto.domain;

import java.util.Iterator;
import java.util.List;

public class Lotto implements Iterable<LottoNumber> {
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

    @Override
    public Iterator<LottoNumber> iterator() {
        return numbers.iterator();
    }
}
