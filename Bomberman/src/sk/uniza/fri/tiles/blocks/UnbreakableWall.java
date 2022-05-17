package sk.uniza.fri.tiles.blocks;

import sk.uniza.fri.characters.Character;
import sk.uniza.fri.powerups.PowerUp;
import sk.uniza.fri.ResourceCollection;
import sk.uniza.fri.tiles.Explosion;
import sk.uniza.fri.tiles.TileObject;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class UnbreakableWall extends TileObject  {

    public UnbreakableWall(int row, int column) {
        super(ResourceCollection.Textures.UNBREAKABLE_WALL.getTexture(), row, column);
    }

    @Override
    public void handleCollision(Character character) {

    }

    @Override
    public void handleCollision(PowerUp powerUp) {

    }

    @Override
    public void handleCollision(Explosion explosion) {
        explosion.destroy();
    }

    @Override
    public void destroy() {

    }
}
