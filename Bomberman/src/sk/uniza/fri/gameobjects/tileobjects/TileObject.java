package sk.uniza.fri.gameobjects.tileobjects;

import sk.uniza.fri.gameobjects.characters.Bomber;
import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gameobjects.GameObject;

import java.awt.image.BufferedImage;

/**
 * Abstract class for tile objects like blocks / walls, bombs, explosions, power ups or markers.
 * This objects are collidable and every tile objects handles collision in their own way.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public abstract class TileObject extends GameObject {

    protected boolean isWalkable;

    /**
     * Creates new tile object at given position without texture. Is mainly for markers.
     * @param row position Y
     * @param column position X
     */
    public TileObject(int row, int column) {
        super(row, column);
    }

    /**
     * Creates new tile object at given position with given texture.
     * @param texture texture for tile object
     * @param row position Y
     * @param column position X
     */
    public TileObject(BufferedImage texture, int row, int column) {
        super(texture, row, column);
    }

    /**
     * Returns if object is walkable.
     * @return isWalkable
     */
    public boolean isWalkable() {
        return this.isWalkable;
    }

    /**
     * This method will be used in near future.
     * @param mob colliding mob
     */
    public abstract void handleCollision(Mob mob);
    public abstract void handleCollision(Bomber bomber);
    public abstract void handleCollision(Explosion explosion);
}
