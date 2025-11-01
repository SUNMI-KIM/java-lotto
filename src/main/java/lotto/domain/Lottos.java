package lotto.domain;

import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto> {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos from(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public List<Integer> countAllMatches(Lotto lotto) {
        return lottos.stream()
                .map(l -> l.countMatches(lotto))
                .toList();
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }
}
