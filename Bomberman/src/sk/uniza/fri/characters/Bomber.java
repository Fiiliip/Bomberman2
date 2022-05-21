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

    private int bombRadius;
    private String color;

    public Bomber(String color, int row, int column) {
        super(ResourceCollection.loadBufferedImage("./resources/textures/Charakter/CHARAKTER_" + color + "_" + Direction.DOWN + ".png"), row, column);

        this.bombRadius = 4;
        this.color = color;

        this.setNumberOfLives(3);
        this.setCountOfSpecialAbility(1);
    }

    public String getColor() {
        return this.color;
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
        if (this.getCountOfSpecialAbility() <= 0) {
            return;
        }

        Map.getMap().setTileObject(new Bomb(this, this.bombRadius, this.getRow(), this.getColumn()));
        this.setCountOfSpecialAbility(this.getCountOfSpecialAbility() - 1);
        this.showTexture();
        this.getMobHUD().updateValues();
    }

    public void addBomb() {
        this.setCountOfSpecialAbility(this.getCountOfSpecialAbility() + 1);
        this.getMobHUD().updateValues();
    }
}
