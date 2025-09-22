import java.util.Queue;

public class StandardStartStrategy implements IStartStrategy {
    private final Queue<Player> players;
    private final Board board;

    public StandardStartStrategy(Queue<Player> players, Board board) {
        this.players = players;
        this.board = board;
    }

    @Override
    public void startGame() {
        System.out.println("Starting Snakes and Ladders Game");
        System.out.println("Board size: " + board.getSize());
        System.out.println("Number of players: " + players.size());
        
        System.out.println("Players:");
        for (Player player : players) {
            System.out.println("- " + player.getName() + " (ID: " + player.getId() + ")");
        }

        System.out.println("Snakes on the board:");
        for (IEntity snake : board.getSnakes()) {
            if (snake instanceof Snake) {
                System.out.println("- " + snake.getName() + ": " + snake.getStartPosition() + " → " + snake.getEndPosition());
            }
        }

        System.out.println("Ladders on the board:");
        for (IEntity ladder : board.getLadders()) {
            if (ladder instanceof Ladder) {
                System.out.println("- " + ladder.getName() + ": " + ladder.getStartPosition() + " → " + ladder.getEndPosition());
            }
        }

        System.out.println("Game Started");
    }
}
