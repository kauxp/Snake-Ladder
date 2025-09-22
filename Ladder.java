public class Ladder implements IEntity {
    private int id;
    private String name;
    private int startPosition;
    private int endPosition;

    public Ladder(int id, String name, int startPosition, int endPosition) {
        if (endPosition <= startPosition) {
            throw new IllegalArgumentException("Ladder end position must be greater than start position");
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
        return "Ladder{id=" + id + ", name='" + name + "', start=" + startPosition + ", end=" + endPosition + "}";
    }
}
