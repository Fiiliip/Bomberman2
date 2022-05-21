package sk.uniza.fri.tiles;

import sk.uniza.fri.characters.Mob;
import sk.uniza.fri.GameObject;
import sk.uniza.fri.powerups.PowerUp;

import java.awt.image.BufferedImage;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public abstract class TileObject extends GameObject {

    protected boolean isWalkable;

    public TileObject(int row, int column) {
        super(row, column);
    }

    public TileObject(BufferedImage texture, int row, int column) {
        super(texture, row, column);
    }

    public boolean isWalkable() {
        return this.isWalkable;
    }

    public abstract void handleCollision(Mob mob);
    public abstract void handleCollision(PowerUp powerUp);
    public abstract void handleCollision(Explosion explosion);
}
