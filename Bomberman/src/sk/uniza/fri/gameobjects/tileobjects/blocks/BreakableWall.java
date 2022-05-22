package sk.uniza.fri.gameobjects.tileobjects.blocks;

import sk.uniza.fri.Bomberman;
import sk.uniza.fri.Map;
import sk.uniza.fri.gameobjects.characters.Bomber;
import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gameobjects.tileobjects.Explosion;
import sk.uniza.fri.gameobjects.tileobjects.TileObject;
import sk.uniza.fri.gui.ResourceCollection;

/**
 * Class for BreakableWall tile.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class BreakableWall extends TileObject {

    /**
     * Creates new breakable wall at given location.
     * @param row at which to create this object
     * @param column at which to create this object
     */
    public BreakableWall(int row, int column) {
        super(ResourceCollection.Textures.BREAKABLE_WALL.getTexture(), row, column);
        this.isWalkable = false;
    }

    @Override
    public void handleCollision(Mob character) {

    }

    @Override
    public void handleCollision(Bomber bomber) {

    }

    /**
     * Destroys current breakable wall and ends current direction of explosion.
     * @param explosion which collides
     */
    @Override
    public void handleCollision(Explosion explosion) {
        this.destroy();
        explosion.setTexture(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture());
        explosion.setExplosionsLeft(0);
    }

    /**
     * Destroys current breakable wall and creates new empty tile at current location.
     */
    @Override
    public void destroy() {
        this.hideTexture();
        Map.getMap().setTileObject(new Empty(this.row, this.column));
        Bomberman.getBomberman().generatePowerUpAt(this.row, this.column);
    }
}
