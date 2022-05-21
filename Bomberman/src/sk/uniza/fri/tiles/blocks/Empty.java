package sk.uniza.fri.tiles.blocks;

import sk.uniza.fri.characters.Mob;
import sk.uniza.fri.powerups.PowerUp;
import sk.uniza.fri.ResourceCollection;
import sk.uniza.fri.tiles.Explosion;
import sk.uniza.fri.tiles.TileObject;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Empty extends TileObject {

    public Empty(int row, int column) {
        super(ResourceCollection.Textures.EMPTY.getTexture(), row, column);
        this.isWalkable = true;
    }

    @Override
    public void handleCollision(Mob character) {

    }

    @Override
    public void handleCollision(PowerUp powerUp) {

    }

    @Override
    public void handleCollision(Explosion explosion) {

    }

    @Override
    public void destroy() {

    }
}
