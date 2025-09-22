import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Game {
    private final Board board;
    private final IStartStrategy startStrategy;
    private final Queue<Player> players;
    private final IWinStrategy winStrategy;
    private final IDiceService dice;
    private boolean gameRunning;

    private Game(Builder builder) {
        this.board = builder.board;
        this.startStrategy = builder.startStrategy;
        this.players = new LinkedList<>(builder.players);
        this.winStrategy = builder.winStrategy;
        this.dice = builder.dice;
        this.gameRunning = false;
    }

    public void startGame() {
        startStrategy.startGame();
        gameRunning = true;
        playGame();
    }

    private void playGame() {

        while (gameRunning) {
            Player currentPlayer = players.poll();
            if (currentPlayer == null) break;

            System.out.println(currentPlayer.getName() + "'s turn ---");
            System.out.println("Current position: " + currentPlayer.getCurrentPosition());

            currentPlayer.move(dice, board);

            winStrategy.checkEndCondition();
            
            if (((StandardWinStrategy) winStrategy).hasWinner()) {
                gameRunning = false;
                break;
            }

            players.offer(currentPlayer);
        }
    }

    public void stopGame() {
        gameRunning = false;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public Board getBoard() {
        return board;
    }

    public Queue<Player> getPlayers() {
        return new LinkedList<>(players);
    }

    public static class Builder {
        private Board board;
        private IStartStrategy startStrategy;
        private List<Player> players = new ArrayList<>();
        private IWinStrategy winStrategy;
        private IDiceService dice;

        public Builder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Builder setStartStrategy(IStartStrategy startStrategy) {
            this.startStrategy = startStrategy;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = new ArrayList<>(players);
            return this;
        }

        public Builder setWinStrategy(IWinStrategy winStrategy) {
            this.winStrategy = winStrategy;
            return this;
        }

        public Builder setDice(IDiceService dice) {
            this.dice = dice;
            return this;
        }

        public Game build() {            
            if (startStrategy == null) {
                startStrategy = new StandardStartStrategy(new LinkedList<>(players), board);
            }
            if (winStrategy == null) {
                winStrategy = new StandardWinStrategy(new LinkedList<>(players), board);
            }
            if (dice == null) {
                dice = DiceFactory.createDice(DiceType.STANDARD_6_SIDED);
            }

            return new Game(this);
        }


    }
}