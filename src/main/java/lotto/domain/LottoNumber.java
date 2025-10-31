package lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int lottoNumber;

    private LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber from(int lottoNumber) {
        return new LottoNumber(lottoNumber);
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return Integer.compare(this.lottoNumber, lottoNumber.lottoNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber lottoNumber)) return false;
        return this.lottoNumber == lottoNumber.lottoNumber;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }
}
