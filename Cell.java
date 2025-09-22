import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final int position;
    private final List<IEntity> entities; 
    private final List<Player> players;   

    public Cell(int position) {
        this.position = position;
        this.entities = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public int getPosition() {
        return position;
    }

    public void addEntity(IEntity entity) {
        entities.add(entity);
    }

    public void removeEntity(IEntity entity) {
        entities.remove(entity);
    }

    public List<IEntity> getEntities() {
        return new ArrayList<>(entities); 
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players); 
    }

    public boolean hasSnake() {
        boolean hasSnake = false;
        for (Object entity : entities) {
            if (entity instanceof Snake) {
                hasSnake = true;
                break; 
            }
        }
        return hasSnake;
    }

    public boolean hasLadder() {
        boolean hasLadder = false;
        for (Object entity : entities) {
            if (entity instanceof Ladder) {
                hasLadder = true;
                break; 
            }
        }
        return hasLadder;
    }

    public IEntity getSnake() {
        Snake snake = null;
        for (Object entity : entities) {
            if (entity instanceof Snake) {
                snake = (Snake) entity;
                break; 
            }
        }

        return snake;
    }

    public IEntity getLadder() {
        Ladder ladder = null;
        for (Object entity : entities) {
            if (entity instanceof Ladder) {
                ladder = (Ladder) entity;
                break;
            }
        }
        return ladder;
    }

    @Override
    public String toString() {
        return "Cell{position=" + position + ", entities=" + entities.size() + ", players=" + players.size() + "}";
    }
}

        