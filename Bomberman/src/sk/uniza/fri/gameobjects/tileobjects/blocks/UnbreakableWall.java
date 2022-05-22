package sk.uniza.fri.gameobjects.tileobjects.blocks;

import sk.uniza.fri.gameobjects.characters.Bomber;
import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gameobjects.tileobjects.Explosion;
import sk.uniza.fri.gameobjects.tileobjects.TileObject;
import sk.uniza.fri.gui.ResourceCollection;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class UnbreakableWall extends TileObject {

    /**
     * Creates new unbreakable wall at given location.
     * @param row at which to create this object
     * @param column at which to create this object
     */
    public UnbreakableWall(int row, int column) {
        super(ResourceCollection.Textures.UNBREAKABLE_WALL.getTexture(), row, column);
        this.isWalkable = false;
    }

    @Override
    public void handleCollision(Mob character) {

    }

    @Override
    public void handleCollision(Bomber bomber) {

    }

    @Override
    public void handleCollision(Explosion explosion) {

    }

    @Override
    public void destroy() {

    }
}
