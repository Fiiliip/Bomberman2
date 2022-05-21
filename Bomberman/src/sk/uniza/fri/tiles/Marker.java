package sk.uniza.fri.tiles;

import sk.uniza.fri.Map;
import sk.uniza.fri.characters.Bomber;
import sk.uniza.fri.characters.Mob;
import sk.uniza.fri.powerups.PowerUp;
import sk.uniza.fri.tiles.blocks.Empty;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Marker extends TileObject {

    private TileObject markedTile;

    public Marker(TileObject tileObject, int row, int column) {
        super(row, column);
        this.isWalkable = true;
        this.markedTile = tileObject;
    }

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
    public void handleCollision(PowerUp powerUp) {

    }

    @Override
    public void handleCollision(Explosion explosion) {

    }

    @Override
    public void destroy() {
        this.hideTexture();
        Map.getMap().setTileObject(new Empty(this.row, this.column));
    }
}
