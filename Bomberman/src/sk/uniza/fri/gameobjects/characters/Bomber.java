package sk.uniza.fri.gameobjects.characters;

import sk.uniza.fri.Map;
import sk.uniza.fri.gui.ResourceCollection;
import sk.uniza.fri.gameobjects.tileobjects.Bomb;

/**
 * A class that manages the logic of Bomber.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class Bomber extends Mob {

    private int explosionRadius;
    private int bombCount;

    private String color;

    /**
     * Creates new Bomber with given parameters.
     * @param color Bomber color
     * @param row position Y
     * @param column position X
     */
    public Bomber(String color, int row, int column) {
        super(ResourceCollection.loadBufferedImage("./resources/textures/Charakter/CHARAKTER_" + color + "_" + Direction.DOWN + ".png"), row, column);
        this.numberOfLives = 3;

        this.explosionRadius = 2;
        this.bombCount = 1;

        this.color = color;
    }

    /**
     * Adds 1 bomb.
     */
    public void addBomb() {
        if (this.bombCount >= 100) {
            return;
        }
        this.bombCount += 1;
        this.getMobHUD().updateValues();
    }

    /**
     *  Increases explosion radius by 1 block.
     */
    public void increaseExplosionRadius() {
        this.explosionRadius += 1;
    }

    /**
     * Returns count of special ability. In this case it returns bomb count.
     * @return bombCount
     */
    @Override
    public int getCountOfSpecialAbility() {
        return this.bombCount;
    }

    /**
     * Moves Bomber 1 tile up and handles collision with the tile.
     */
    @Override
    public void moveUp() {
        this.moveTo(Direction.UP);
        Map.getMap().getTileObjects()[this.row][this.column].handleCollision(this);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.UP + ".png");
    }

    /**
     * Moves Bomber 1 tile down and handles collision with the tile.
     */
    @Override
    public void moveDown() {
        this.moveTo(Direction.DOWN);
        Map.getMap().getTileObjects()[this.row][this.column].handleCollision(this);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.DOWN + ".png");
    }

    /**
     * Moves Bomber 1 tile left and handles collision with the tile.
     */
    @Override
    public void moveLeft() {
        this.moveTo(Direction.LEFT);
        Map.getMap().getTileObjects()[this.row][this.column].handleCollision(this);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.LEFT + ".png");
    }

    /**
     * Moves Bomber 1 tile right and handles collision with the tile.
     */
    @Override
    public void moveRight() {
        this.moveTo(Direction.RIGHT);
        Map.getMap().getTileObjects()[this.row][this.column].handleCollision(this);
        this.setTexture("./resources/textures/Charakter/CHARAKTER_" + this.color + "_" + Direction.RIGHT + ".png");
    }

    /**
     * Places bomb at current tile if Bomber has enough bombs.
     */
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
