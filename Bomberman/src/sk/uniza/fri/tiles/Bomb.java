package sk.uniza.fri.tiles;

import sk.uniza.fri.characters.Bomber;
import sk.uniza.fri.characters.Character;
import sk.uniza.fri.characters.Direction;
import sk.uniza.fri.Manazer;
import sk.uniza.fri.Map;
import sk.uniza.fri.powerups.PowerUp;
import sk.uniza.fri.ResourceCollection;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Bomb extends TileObject {

    private static final int TIME_TO_EXPLOSION = 10;

    private Bomber owner;
    private Manazer manazer;

    private int tickCount;
    private int bombRadius;

    public Bomb(Bomber owner, int bombRadius, int row, int column) {
        super(ResourceCollection.Textures.BOMB.getTexture(), row, column);

        Map.getMap().setTileObject(this);

        this.owner = owner;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

        this.tickCount = 0;
        this.bombRadius = bombRadius;
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
        Map.getMap().setTileObject(new Explosion(ResourceCollection.Textures.EXPLOSION_START_BIG.getTexture(), this.getRow(), this.getColumn(), Direction.NONE, this.bombRadius));
        this.manazer.prestanSpravovatObjekt(this);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void handleCollision(Character character) {
        character.takeLife();
    }

    @Override
    public void handleCollision(PowerUp powerUp) {

    }

    @Override
    public void handleCollision(Explosion explosion) {

    }
}
