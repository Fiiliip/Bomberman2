package sk.uniza.fri.tiles.blocks;

import sk.uniza.fri.Bomberman;
import sk.uniza.fri.Map;
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
public class BreakableWall extends TileObject {

    public BreakableWall(int row, int column) {
        super(ResourceCollection.Textures.BREAKABLE_WALL.getTexture(), row, column);
        this.isWalkable = false;
    }

    @Override
    public void handleCollision(Mob character) {

    }

    @Override
    public void handleCollision(PowerUp powerUp) {

    }

    @Override
    public void handleCollision(Explosion explosion) {
        this.destroy();
        explosion.setTexture(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture());
        explosion.setExplosionsLeft(0);
    }

    @Override
    public void destroy() {
        this.hideTexture();
        Map.getMap().setTileObject(new Empty(this.row, this.column));
        Bomberman.getBomberman().generatePowerUpAt(this.row, this.column);
    }
}
