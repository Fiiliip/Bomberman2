package sk.uniza.fri;

import sk.uniza.fri.gui.Obrazok;

import java.awt.image.BufferedImage;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public abstract class GameObject {

    private Obrazok texture;

    private int row;
    private int column;

    public GameObject(BufferedImage texture, int row, int column) {
        this.texture = new Obrazok(texture);

        this.row = row;
        this.column = column;

        this.texture.setPosition(column * 30, row * 30 + 30);
        this.texture.zobraz();
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setPosition(int newRow, int newColumn) {
        this.row = newRow;
        this.column = newColumn;

        int x = this.column * 30;
        int y = this.row * 30 + 30;

        this.texture.setPosition(x, y);
    }

    public void rotateTexture(int angle) {
        this.texture.zmenUhol(angle);
    }

    public void setTexture(String fileWithTexture) {
        this.texture.zmenObrazok(fileWithTexture);
    }

    public void setTexture(BufferedImage bufferedImage) {
        this.texture.zmenObrazok(bufferedImage);
    }

    public void showTexture() {
        this.texture.zobraz();
    }

    public void hideTexture() {
        this.texture.skry();
    }

    public abstract void destroy();
}
