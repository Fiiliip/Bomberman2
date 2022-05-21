package sk.uniza.fri.tiles;

import sk.uniza.fri.characters.Bomber;
import sk.uniza.fri.characters.Mob;
import sk.uniza.fri.characters.Direction;
import sk.uniza.fri.Manazer;
import sk.uniza.fri.Map;
import sk.uniza.fri.powerups.PowerUp;
import sk.uniza.fri.ResourceCollection;
import sk.uniza.fri.tiles.blocks.Empty;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Bomb extends TileObject {

    private static final int TIME_TO_EXPLOSION = 10;

    private Bomber owner;
    private int bombRadius;

    private int tickCount;
    private Manazer manazer;

    public Bomb(Bomber owner, int bombRadius, int row, int column) {
        super(ResourceCollection.Textures.BOMB.getTexture(), row, column);
        this.isWalkable = false;

        this.owner = owner;
        this.bombRadius = bombRadius;

        Map.getMap().setTileObject(this);

        this.tickCount = 0;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }

    public void tik() {
        this.tickCount++;
        if (this.tickCount >= TIME_TO_EXPLOSION) {
            this.explode();
        }
    }

    public void explode() {
        this.destroy();
        this.owner.addBomb();
        Map.getMap().setTileObject(new Marker(this, this.row, this.column));
        Map.getMap().setTileObject(new Explosion(Direction.NONE, this.row, this.column, this.bombRadius));
    }

    @Override
    public void handleCollision(Mob character) {

    }

    @Override
    public void handleCollision(Bomber bomber) {

    }

    @Override
    public void handleCollision(PowerUp powerUp) {

    }

    @Override
    public void handleCollision(Explosion explosion) {
        this.explode();
    }

    @Override
    public void destroy() {
        this.hideTexture();
        this.manazer.prestanSpravovatObjekt(this);
        Map.getMap().setTileObject(new Empty(this.row, this.column));
    }
}
