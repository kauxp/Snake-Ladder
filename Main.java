import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Game game = createSampleGame();
        
        game.startGame();
        
    }

    private static Game createSampleGame() {
        Board board = new Board.Builder()
            .setSize(100)
            .addSnakes(Arrays.asList(
                new Snake(1, "Python", 99, 54),
                new Snake(2, "Cobra", 95, 72),
                new Snake(3, "Viper", 87, 24),
                new Snake(4, "Anaconda", 62, 19),
                new Snake(5, "Adder", 46, 25),
                new Snake(6, "Rattler", 49, 11),
                new Snake(7, "Mamba", 16, 6)
            ))
            .addLadders(Arrays.asList(
                new Ladder(1, "Tall Ladder", 1, 38),
                new Ladder(2, "Medium Ladder", 4, 14),
                new Ladder(3, "Short Ladder", 9, 21),
                new Ladder(4, "Climb Up", 21, 42),
                new Ladder(5, "Sky High", 28, 84),
                new Ladder(6, "Quick Rise", 36, 44)
            ))
            .build();

        IDiceService dice = DiceFactory.createDice(DiceType.STANDARD_6_SIDED);

        IMoveStrategy standardMove = new StandardMoveStrategy();
        
        Player player1 = new Player(1, "Alice", standardMove);
        Player player2 = new Player(2, "Bob", standardMove);
        Player player3 = new Player(3, "Charlie", standardMove);

        Game game = new Game.Builder()
            .setBoard(board)
            .setDice(dice)
            .addPlayer(player1)
            .addPlayer(player2)
            .addPlayer(player3)
            .build();

        return game;
    }
}
