package sk.uniza.fri.gameobjects.tileobjects;

import sk.uniza.fri.Map;
import sk.uniza.fri.gameobjects.characters.Bomber;
import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gameobjects.tileobjects.blocks.Empty;

/**
 * Class for Marker logic. Marker is object, that marks specific tile with tile stored in markedTile.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class Marker extends TileObject {

    private TileObject markedTile;

    /**
     * Creates new Marker at given position, with given parameters.
     * @param tileObject tile, that is marked
     * @param row position Y
     * @param column position X
     */
    public Marker(TileObject tileObject, int row, int column) {
        super(row, column);
        this.isWalkable = true;
        this.markedTile = tileObject;
    }

    /**
     * Return marked tile.
     * @return marked tile
     */
    public TileObject getMarkedTile() {
        return this.markedTile;
    }

    @Override
    public void handleCollision(Mob mob) {

    }

    @Override
    public void handleCollision(Bomber bomber) {

    }

    @Override
    public void handleCollision(Explosion explosion) {

    }

    /**
     * Destroys marker and sets empty tile at current position.
     */
    @Override
    public void destroy() {
        this.hideTexture();
        Map.getMap().setTileObject(new Empty(this.row, this.column));
    }
}
