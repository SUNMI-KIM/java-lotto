package lotto.domain;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public static Lottos from(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public List<Integer> countAllMatches(Lotto lotto) {
        return lottos.stream()
                .map(l -> l.countMatches(lotto))
                .toList();
    }

    public List<Boolean> countAllNumberMatches(LottoNumber lottoNumber) {
        return lottos.stream()
                .map(lotto -> lotto.contains(lottoNumber))
                .toList();
    }

    public Stream<Lotto> stream() {
        return lottos.stream();
    }

}
