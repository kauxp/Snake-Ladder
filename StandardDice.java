import java.util.Random;

public class StandardDice implements IDiceService {
    private final int sides;
    private final Random random;

    public StandardDice(int sides) {
        this.sides = sides;
        this.random = new Random();
    }

    @Override
    public int rollDice() {
        return random.nextInt(sides) + 1;
    }

    @Override
    public int getSides() {
        return sides;
    }
}
