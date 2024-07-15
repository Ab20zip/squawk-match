import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MatchService {
    private final List<Parrot> swipedRight = new ArrayList<>();
    private final List<Parrot> swipedLeft = new ArrayList<>();
    private final List<Parrot> parrots = new ArrayList<>();

    public void registerParrot(Parrot parrot) {
        parrots.add(parrot);
    }

    public List<Parrot> getParrots() {
        return parrots;
    }

    public List<Parrot> findMatches(Parrot parrot) {
        return parrots.stream()
                .filter(p -> p != parrot && p.getAge() == parrot.getAge() && !swipedLeft.contains(p) && !swipedRight.contains(p))
                .collect(Collectors.toList());
    }

    public void swipeRight(Parrot parrot, Parrot match) {
        swipedRight.add(match);
    }

    public void swipeLeft(Parrot parrot, Parrot match) {
        swipedLeft.add(match);
    }
}
