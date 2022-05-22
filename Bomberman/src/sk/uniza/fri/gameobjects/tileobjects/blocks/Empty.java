package sk.uniza.fri.gameobjects.tileobjects.blocks;

import sk.uniza.fri.gameobjects.characters.Bomber;
import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gameobjects.tileobjects.Explosion;
import sk.uniza.fri.gameobjects.tileobjects.TileObject;
import sk.uniza.fri.gui.ResourceCollection;

/**
 * Class for Empty tile.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class Empty extends TileObject {

    /**
     * Creates new empty block at given location.
     * @param row at which to create this object
     * @param column at which to create this object
     */
    public Empty(int row, int column) {
        super(ResourceCollection.Textures.EMPTY.getTexture(), row, column);
        this.isWalkable = true;
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
