package sk.uniza.fri.powerups;

import sk.uniza.fri.tiles.TileObject;

import java.awt.image.BufferedImage;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public abstract class PowerUp extends TileObject {

    public PowerUp(BufferedImage texture, int x, int y) {
        super(texture, x, y);
    }
}
