package sk.uniza.fri.characters;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    NONE(0, 0);

    private int row;
    private int column;

    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
}
