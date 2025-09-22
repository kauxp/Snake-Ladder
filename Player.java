public class Player {
    private final int id;
    private final String name;
    private int currentPosition;
    private IMoveStrategy moveStrategy;
    private boolean hasWon;

    public Player(int id, String name, IMoveStrategy moveStrategy) {
        this.id = id;
        this.name = name;
        this.currentPosition = 0;
        this.moveStrategy = moveStrategy;
        this.hasWon = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int position) {
        this.currentPosition = position;
    }

    public IMoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(IMoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public void setWon(boolean won) {
        this.hasWon = won;
    }

    public int move(IDiceService dice, Board board) {
        return moveStrategy.movePlayer(this, dice, board);
    }

    @Override
    public String toString() {
        return "Player{id=" + id + ", name='" + name + "', position=" + currentPosition + ", hasWon=" + hasWon + "}";
    }
}