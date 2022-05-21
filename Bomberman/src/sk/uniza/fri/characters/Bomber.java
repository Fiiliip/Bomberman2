package sk.uniza.fri.characters;

import sk.uniza.fri.Map;
import sk.uniza.fri.ResourceCollection;
import sk.uniza.fri.tiles.Bomb;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Bomber extends Mob {

    private int explosionRadius;
    private int bombCount;

    private String color;

    public Bomber(String color, int row, int column) {
        super(ResourceCollection.loadBufferedImage("./resources/textures/Charakter/CHARAKTER_" + color + "_" + Direction.DOWN + ".png"), row, column);
        this.numberOfLives = 3;

        this.explosionRadius = 7;
        this.bombCount = 4;

        this.color = color;
    }

    public void addBomb() {
        this.bombCount += 1;
        this.getMobHUD().updateValues();
    }

    public void increaseExplosionRadius() {
        this.explosionRadius += 1;
    }

    @Override
    public int getCountOfSpecialAbility() {
        return this.bombCount;
    }

    @Override
    public void moveUp() {
        this.moveTo(Direction.UP);
        Map.getMap().getTileObjects()[this.row][this.column].handleCollision(this);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.UP + ".png");
    }

    @Override
    public void moveDown() {
        this.moveTo(Direction.DOWN);
        Map.getMap().getTileObjects()[this.row][this.column].handleCollision(this);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.DOWN + ".png");
    }

    @Override
    public void moveLeft() {
        this.moveTo(Direction.LEFT);
        Map.getMap().getTileObjects()[this.row][this.column].handleCollision(this);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.LEFT + ".png");
    }

    @Override
    public void moveRight() {
        this.moveTo(Direction.RIGHT);
        Map.getMap().getTileObjects()[this.row][this.column].handleCollision(this);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.RIGHT + ".png");
    }

    @Override
    public void action() {
        if (this.bombCount <= 0) {
            return;
        }

        Map.getMap().setTileObject(new Bomb(this, this.explosionRadius, this.getRow(), this.getColumn()));
        this.bombCount -= 1;
        this.showTexture();
        this.getMobHUD().updateValues();
    }
}
