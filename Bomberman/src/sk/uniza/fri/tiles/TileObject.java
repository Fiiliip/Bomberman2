package sk.uniza.fri.tiles;

import sk.uniza.fri.characters.Character;
import sk.uniza.fri.GameObject;
import sk.uniza.fri.powerups.PowerUp;

import java.awt.image.BufferedImage;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public abstract class TileObject extends GameObject {

    public TileObject(BufferedImage texture, int row, int column) {
        super(texture, row, column);
    }

    public abstract void handleCollision(Character character);
    public abstract void handleCollision(PowerUp powerUp);
    public abstract void handleCollision(Explosion explosion);
}
