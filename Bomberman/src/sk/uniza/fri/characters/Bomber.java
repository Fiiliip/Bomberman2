package sk.uniza.fri.characters;

import sk.uniza.fri.Manazer;
import sk.uniza.fri.Map;
import sk.uniza.fri.ResourceCollection;
import sk.uniza.fri.tiles.Bomb;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Bomber extends Character {

    private Manazer manazer;

    private int bombRadius;
    private int bombCount;

    private String color;

    public Bomber(String color, int row, int column) {
        super(ResourceCollection.loadBufferedImage("./resources/textures/Charakter/CHARAKTER_" + color + "_" + Direction.DOWN + ".png"), row, column);

        this.color = color;

        this.setNumberOfLives(3);

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

        this.bombRadius = 3;
        this.bombCount = 1;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void moveUp() {
        this.moveTo(Direction.UP);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.UP + ".png");
    }

    @Override
    public void moveDown() {
        this.moveTo(Direction.DOWN);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.DOWN + ".png");
    }

    @Override
    public void moveLeft() {
        this.moveTo(Direction.LEFT);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.LEFT + ".png");
    }

    @Override
    public void moveRight() {
        this.moveTo(Direction.RIGHT);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.RIGHT + ".png");
    }

    @Override
    public void action() {
        if (this.bombCount <= 0) {
            return;
        }

        Map.getMap().setTileObject(new Bomb(this, this.bombRadius, this.getRow(), this.getColumn()));
        this.bombCount--;
        this.showTexture();
        // TODO: HUD aktualizuj hodnoty
    }

    public void addBomb() {
        this.bombCount++;
    }
}
