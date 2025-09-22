public class DiceFactory {

    public static IDiceService createDice(DiceType type) {
        switch (type) {
            case STANDARD_6_SIDED:
                return new StandardDice(6);
            case STANDARD_8_SIDED:
                return new StandardDice(8);
            case STANDARD_12_SIDED:
                return new StandardDice(12);
            default:
                return new StandardDice(6); 
        }
    }

    public static IDiceService createCustomDice(int sides) {
        if (sides < 1) {
            throw new IllegalArgumentException("Dice must have at least 1 side");
        }
        return new StandardDice(sides);
    }
}
