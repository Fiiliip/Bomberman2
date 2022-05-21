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

    protected int row;
    protected int column;

    public GameObject(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public GameObject(BufferedImage texture, int row, int column) {
        this.texture = new Obrazok(texture);

        this.row = row;
        this.column = column;

        this.texture.setPosition(this.column * 30, this.row * 30 + 30);
        this.texture.zobraz();
    }

    public void setTexture(String fileWithTexture) {
        if (this.texture == null) {
            this.texture = new Obrazok(fileWithTexture);
            this.texture.zobraz();
        } else {
            this.texture.zmenObrazok(fileWithTexture);
        }

        this.texture.setPosition(this.column * 30, this.row * 30 + 30);
    }

    public void setTexture(BufferedImage bufferedImage) {
        if (this.texture == null) {
            this.texture = new Obrazok(bufferedImage);
            this.texture.zobraz();
        } else {
            this.texture.zmenObrazok(bufferedImage);
        }

        this.texture.setPosition(this.column * 30, this.row * 30 + 30);
    }

    public Obrazok getTexture() {
        return this.texture;
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

    public void showTexture() {
        this.texture.zobraz();
    }

    public void hideTexture() {
        this.texture.skry();
    }

    public abstract void destroy();
}
