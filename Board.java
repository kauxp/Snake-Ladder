import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final int size;
    private final Map<Integer, Cell> cells;
    private final List<IEntity> snakes;
    private final List<IEntity> ladders;

    private Board(Builder builder) {
        this.size = builder.size;
        this.cells = new HashMap<>();
        this.snakes = new ArrayList<>(builder.snakes);
        this.ladders = new ArrayList<>(builder.ladders);
        
        initializeCells();
        placeEntities();
    }

    private void initializeCells() {
        for (int i = 1; i <= size; i++) {
            cells.put(i, new Cell(i));
        }
    }

    public void displayBoard() {
        
    }

    private void placeEntities() {
        for (IEntity entity : snakes) {
            Cell startCell = cells.get(entity.getStartPosition());
            if (startCell != null) {
                startCell.addEntity(entity);
            }
        }

        for (IEntity entity : ladders) {
            Cell startCell = cells.get(entity.getStartPosition());
            if (startCell != null) {
                startCell.addEntity(entity);
            }
            
        }
    }

    public Cell getCell(int position) {
        return cells.get(position);
    }

    public int getSize() {
        return size;
    }

    public List<IEntity> getSnakes() {
        return new ArrayList<>(snakes);
    }

    public List<IEntity> getLadders() {
        return new ArrayList<>(ladders);
    }

    public boolean isValidPosition(int position) {
        return position >= 1 && position <= size;
    }

    public int getNewPositionAfterSnakeOrLadder(int position) {
        Cell cell = getCell(position);
        if (cell == null) return position;

        if (cell.hasSnake()) {
            IEntity snake = cell.getSnake();
            return snake.getEndPosition();
        } else if (cell.hasLadder()) {
            IEntity ladder = cell.getLadder();
            return ladder.getEndPosition();
        }

        return position;
    }

    public static class Builder {
        private int size = 100; // Default board size
        private List<IEntity> snakes = new ArrayList<>();
        private List<IEntity> ladders = new ArrayList<>();

        public Builder setSize(int size) {
            if (size <= 0) {
                throw new IllegalArgumentException("Board size must be positive");
            }
            this.size = size;
            return this;
        }

        public Builder addSnake(Snake snake) {
            if (snake.getStartPosition() > size || snake.getEndPosition() < 1) {
                throw new IllegalArgumentException("Snake positions must be within board boundaries");
            }
            for (IEntity s : snakes) {
                if (s.getStartPosition() == snake.getStartPosition() ||
                    s.getEndPosition() == snake.getEndPosition()) {
                    throw new IllegalArgumentException("Snake cannot overlap with another snake");
                }
            }
            for (IEntity l : ladders) {
                if (l.getStartPosition() == snake.getStartPosition() ||
                    l.getEndPosition() == snake.getEndPosition()) {
                    throw new IllegalArgumentException("Snake cannot overlap with a ladder");
                }
            }

            this.snakes.add(snake);
            return this;
        }

        public Builder addLadder(Ladder ladder) {
            if (ladder.getStartPosition() > size || ladder.getEndPosition() > size) {
                throw new IllegalArgumentException("Ladder positions must be within board boundaries");
            }
            for (IEntity l : ladders) {
                if (l.getStartPosition() == ladder.getStartPosition() ||
                    l.getEndPosition() == ladder.getEndPosition()) {
                    throw new IllegalArgumentException("Ladder cannot overlap with another ladder");
                }
            }
            for (IEntity s : snakes) {
                if (s.getStartPosition() == ladder.getStartPosition() ||
                    s.getEndPosition() == ladder.getEndPosition()) {
                    throw new IllegalArgumentException("Ladder cannot overlap with a snake");
                }
            }
            this.ladders.add(ladder);
            return this;
        }

        public Builder addSnakes(List<Snake> snakes) {
            for (Snake snake : snakes) {
                addSnake(snake);
            }
            return this;
        }

        public Builder addLadders(List<Ladder> ladders) {
            for (Ladder ladder : ladders) {
                addLadder(ladder);
            }
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}