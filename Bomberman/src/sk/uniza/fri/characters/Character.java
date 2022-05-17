package sk.uniza.fri.characters;

import sk.uniza.fri.GameObject;
import sk.uniza.fri.Map;
import sk.uniza.fri.tiles.blocks.BreakableWall;
import sk.uniza.fri.tiles.blocks.UnbreakableWall;
import sk.uniza.fri.tiles.Bomb;
import sk.uniza.fri.tiles.TileObject;

import java.awt.image.BufferedImage;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public abstract class Character extends GameObject {

    private int numberOfLives;
    private boolean isImmune;

    private int row;
    private int column;

    public Character(BufferedImage texture, int row, int column) {
        super(texture, row, column);
        this.isImmune = false;

        this.row = row;
        this.column = column;
    }

    public int getNumberOfLives() {
        return this.numberOfLives;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    public boolean isImmune() {
        return this.isImmune;
    }

    public void setImmune(boolean isImmune) {
        this.isImmune = isImmune;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void moveTo(Direction n) {

        int newRow = this.row + n.getRow();
        int newColumn = this.column + n.getColumn();

        TileObject[][] tileObjects = Map.getMap().getTileObjects();
        // TODO: zmen tuto podmienku na nieco, kde budem kontrolovat, ci je jednotlivy tile solid, alebo take nieco
        if (tileObjects[newRow][newColumn] instanceof BreakableWall || tileObjects[newRow][newColumn] instanceof UnbreakableWall || tileObjects[newRow][newColumn] instanceof Bomb) {
            return;
        }

        this.setPosition(newRow, newColumn);
        tileObjects[newRow][newColumn].handleCollision(this);

        this.row = newRow;
        this.column = newColumn;
    }

    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void action();

    public void takeLife() {
        if (this.isImmune) {
            return;
        }

        if (--this.numberOfLives <= 0) {
            // TODO: destroy
        } else {
            this.isImmune = true;
        }
    }
}
