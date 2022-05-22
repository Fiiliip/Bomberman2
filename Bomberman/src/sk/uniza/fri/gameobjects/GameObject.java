package sk.uniza.fri.gameobjects;

import sk.uniza.fri.gui.shapes.Obrazok;

import java.awt.image.BufferedImage;

/**
 * Abstract game object class which is extended by all game objects.
 * Stores all basic properties about game objects.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public abstract class GameObject {

    private Obrazok texture;

    protected int row;
    protected int column;

    /**
     * Creates new game object at given position. It is for game objects, that do not have texture.
     * @param row position Y
     * @param column position X
     */
    public GameObject(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Creates new game object at given position. It is for game objects, that have texture.
     * @param row position Y
     * @param column position X
     */
    public GameObject(BufferedImage texture, int row, int column) {
        this.texture = new Obrazok(texture);

        this.row = row;
        this.column = column;

        this.texture.setPosition(this.column * 30, this.row * 30 + 30);
        this.texture.zobraz();
    }

    /**
     * Sets texture for game object. This texture has to be loaded from file.
     * @param fileWithTexture fully specified path to file with texture
     */
    public void setTexture(String fileWithTexture) {
        if (this.texture == null) {
            this.texture = new Obrazok(fileWithTexture);
            this.texture.zobraz();
        } else {
            this.texture.zmenObrazok(fileWithTexture);
        }

        this.texture.setPosition(this.column * 30, this.row * 30 + 30);
    }

    /**
     * Sets texture for game object. This is for texture, which is already loaded in game.
     * @param bufferedImage loaded texture
     */
    public void setTexture(BufferedImage bufferedImage) {
        if (this.texture == null) {
            this.texture = new Obrazok(bufferedImage);
            this.texture.zobraz();
        } else {
            this.texture.zmenObrazok(bufferedImage);
        }

        this.texture.setPosition(this.column * 30, this.row * 30 + 30);
    }

    /**
     * Returns texture.
     * @return texture
     */
    public Obrazok getTexture() {
        return this.texture;
    }

    /**
     * Returns current row / position Y.
     * @return row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns current column / position X.
     * @return column
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Sets new position with given parameters.
     * @param newRow new row / position Y to set this game object
     * @param newColumn new column / position X to set this game object
     */
    public void setPosition(int newRow, int newColumn) {
        this.row = newRow;
        this.column = newColumn;

        int x = this.column * 30;
        int y = this.row * 30 + 30;

        this.texture.setPosition(x, y);
    }

    /**
     * Rotates texture by given angle.
     * @param angle angle by which to rotate this texture
     */
    public void rotateTexture(int angle) {
        this.texture.zmenUhol(angle);
    }

    /**
     * Shows current texture.
     */
    public void showTexture() {
        this.texture.zobraz();
    }

    /**
     * Hides current texture.
     */
    public void hideTexture() {
        this.texture.skry();
    }

    public abstract void destroy();
}
