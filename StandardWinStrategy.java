import java.util.Queue;

public class StandardWinStrategy implements IWinStrategy {
    private final Queue<Player> players;
    private final Board board;

    public StandardWinStrategy(Queue<Player> players, Board board) {
        this.players = players;
        this.board = board;
    }

    @Override
    public void checkEndCondition() {
        for (Player player : players) {
            if (player.getCurrentPosition() >= board.getSize()) {
                player.setWon(true);
                return;
            }
        }
    }

    public boolean hasWinner() {
        for (Player player : players) {
            if (player.hasWon()) {
                System.out.println("CONGRATULATIONS! " + player.getName() + " has won the game!");
                return true;
            }
        }
        return false;
    }

}
