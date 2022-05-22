package sk.uniza.fri.gameobjects.tileobjects;

import sk.uniza.fri.gameobjects.characters.Bomber;
import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gameobjects.characters.Direction;
import sk.uniza.fri.Manazer;
import sk.uniza.fri.Map;
import sk.uniza.fri.gui.ResourceCollection;
import sk.uniza.fri.gameobjects.tileobjects.blocks.Empty;

/**
 * Class which manages logic for Bomb.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class Bomb extends TileObject {

    private static final int TIME_TO_EXPLOSION = 10;

    private Bomber owner;
    private int explosionRadius;

    private int tickCount;
    private Manazer manazer;

    /**
     * Creates new bomb with specified parameters.
     * @param owner bomber, which placed this bomb
     * @param explosionRadius radius of explosion
     * @param row position Y
     * @param column position X
     */
    public Bomb(Bomber owner, int explosionRadius, int row, int column) {
        super(ResourceCollection.Textures.BOMB.getTexture(), row, column);
        this.isWalkable = false;

        this.owner = owner;
        this.explosionRadius = explosionRadius;

        Map.getMap().setTileObject(this);

        this.tickCount = 0;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }

    /**
     * Method, which is called every x (set in class Manazer, current 0.25 seconds) nanosecond by Manazer.
     */
    public void tik() {
        this.tickCount++;
        if (this.tickCount >= TIME_TO_EXPLOSION) {
            this.explode();
        }
    }

    /**
     * Destroys bomb and creates marker and explosion at current location.
     */
    public void explode() {
        this.destroy();
        this.owner.addBomb();
        Map.getMap().setTileObject(new Marker(this, this.row, this.column));
        Map.getMap().setTileObject(new Explosion(Direction.NONE, this.row, this.column, this.explosionRadius));
    }

    @Override
    public void handleCollision(Mob character) {

    }

    @Override
    public void handleCollision(Bomber bomber) {

    }

    /**
     * Explodes on collision with explosion.
     * @param explosion that collides
     */
    @Override
    public void handleCollision(Explosion explosion) {
        this.explode();
    }

    /**
     * Destroys bomb and set new empty tile at current location.
     */
    @Override
    public void destroy() {
        this.hideTexture();
        this.manazer.prestanSpravovatObjekt(this);
        Map.getMap().setTileObject(new Empty(this.row, this.column));
    }
}
