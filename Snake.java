public class Snake implements IEntity {
    private int id;
    private String name;
    private int startPosition;
    private int endPosition;

    public Snake(int id, String name, int startPosition, int endPosition) {
        if (endPosition >= startPosition) {
            throw new IllegalArgumentException("Snake end position must be less than start position");
        }
        this.id = id;
        this.name = name;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStartPosition() {
        return startPosition;
    }

    @Override
    public int getEndPosition() {
        return endPosition;
    }
    @Override
    public String toString() {
        return "Snake{id=" + id + ", name='" + name + "', start=" + startPosition + ", end=" + endPosition + "}";
    }
}
