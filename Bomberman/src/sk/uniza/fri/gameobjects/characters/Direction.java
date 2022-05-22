package sk.uniza.fri.gameobjects.characters;

/**
 * Enumerator, that stores values for movement to different directions.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    NONE(0, 0);

    private final int row;
    private final int column;

    /**
     * Creates Direction with given parameters.
     * @param row
     * @param column
     */
    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the row value by which is Object using this Direction moved.
     * @return row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the column value by which is Object using this Direction moved.
     * @return row
     */
    public int getColumn() {
        return this.column;
    }
}
