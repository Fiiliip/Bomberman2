package sk.uniza.fri.tiles;

import sk.uniza.fri.Bomberman;
import sk.uniza.fri.Manazer;
import sk.uniza.fri.characters.Mob;
import sk.uniza.fri.characters.Direction;
import sk.uniza.fri.Map;
import sk.uniza.fri.powerups.PowerUp;
import sk.uniza.fri.ResourceCollection;
import sk.uniza.fri.tiles.blocks.Empty;
import sk.uniza.fri.tiles.blocks.UnbreakableWall;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Explosion extends TileObject {

    private static final int TIME_FOR_EXPLOSION = 8;

    private TileObject originalTileObject;

    private Direction direction;
    private int explosionsLeft;

    private int tickCount;
    private Manazer manazer;

    public Explosion(Direction direction, int row, int column, int explosionsLeft) {
        super(row, column);
        this.isWalkable = true;

        this.direction = direction;
        this.explosionsLeft = explosionsLeft - 1;

        this.setTextureAndRotation();

        Bomberman.getBomberman().takeMobsLivesAt(this.row, this.column);

         Map.getMap().getTileObjects()[this.row][this.column].handleCollision(this);
        // Ak na tomto Tile existuje už explózia, tak si zoberiem jej originálny Tile, ktorý pri zničení tejto explózie vrátim späť.
        if (Map.getMap().getTileObjects()[this.row][this.column] instanceof Explosion) {
            this.originalTileObject = ((Explosion)Map.getMap().getTileObjects()[this.row][this.column]).originalTileObject;
        } else {
            this.originalTileObject = Map.getMap().getTileObjects()[this.row][this.column];
        }
        Map.getMap().setTileObject(this);

        if (this.explosionsLeft > 0 && !(Map.getMap().getTileObjects()[this.row + this.direction.getRow()][this.column + this.direction.getColumn()] instanceof UnbreakableWall)) {
            this.createNextExplosion();
        }

        this.tickCount = 0;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }

    public void tik() {
        this.tickCount++;
        if (this.tickCount >= TIME_FOR_EXPLOSION) {
            this.destroy();
        }
    }

    public int getTickCount() {
        return this.tickCount;
    }

    public void createNextExplosion() {
        if (this.direction != Direction.NONE) {
            new Explosion(this.direction, this.row + this.direction.getRow(), this.column + this.direction.getColumn(), this.explosionsLeft);
        } else {
            if (!(Map.getMap().getTileObjects()[this.row + Direction.UP.getRow()][this.column + Direction.UP.getColumn()] instanceof UnbreakableWall)) {
                new Explosion(Direction.UP, this.row + Direction.UP.getRow(), this.column + Direction.UP.getColumn(), this.explosionsLeft);
            }

            if (!(Map.getMap().getTileObjects()[this.row + Direction.DOWN.getRow()][this.column + Direction.DOWN.getColumn()] instanceof UnbreakableWall)) {
                new Explosion(Direction.DOWN, this.row + Direction.DOWN.getRow(), this.column + Direction.DOWN.getColumn(), this.explosionsLeft);
            }

            if (!(Map.getMap().getTileObjects()[this.row + Direction.LEFT.getRow()][this.column + Direction.LEFT.getColumn()] instanceof UnbreakableWall)) {
                new Explosion(Direction.LEFT, this.row + Direction.LEFT.getRow(), this.column + Direction.LEFT.getColumn(), this.explosionsLeft);
            }

            if (!(Map.getMap().getTileObjects()[this.row + Direction.RIGHT.getRow()][this.column + Direction.RIGHT.getColumn()] instanceof UnbreakableWall)) {
                new Explosion(Direction.RIGHT, this.row + Direction.RIGHT.getRow(), this.column + Direction.RIGHT.getColumn(), this.explosionsLeft);
            }
        }
    }

    public void setTextureAndRotation() {
        if (this.direction == Direction.NONE) {
            this.setTexture(ResourceCollection.Textures.EXPLOSION_START_BIG.getTexture());
        } else if (this.explosionsLeft == 0 || Map.getMap().getTileObjects()[this.row + this.direction.getRow()][this.column + this.direction.getColumn()] instanceof UnbreakableWall) {
            this.setTexture(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture());
        } else {
            this.setTexture(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture());
        }

        switch (this.direction) {
            case UP:
                this.rotateTexture(-90);
                break;
            case DOWN:
                this.rotateTexture(90);
                break;
            case LEFT:
                this.rotateTexture(180);
                break;
            case RIGHT:
                this.rotateTexture(0);
                break;
            case NONE:
                break;
        }
    }

    public void setExplosionsLeft(int explosionsLeft) {
        this.explosionsLeft = explosionsLeft;
    }

    @Override
    public void handleCollision(Mob mob) {
        mob.takeLife();
    }

    @Override
    public void handleCollision(PowerUp powerUp) {
    }

    @Override
    public void handleCollision(Explosion explosion) {
        this.setTexture(ResourceCollection.Textures.EXPLOSION_START_BIG.getTexture());
    }

    @Override
    public void destroy() {
        this.hideTexture();
        this.explosionsLeft = 0;
        this.manazer.prestanSpravovatObjekt(this);

        // Skontrolujem, či medzitým tu neprišla nová explózia. Ak áno, tak túto aktuálnu explóziu iba zničím a nezobrazím namiesto nej iný Tile.
        if (Map.getMap().getTileObjects()[this.row][this.column] == this) {
            if (this.originalTileObject == null) {
                Map.getMap().setTileObject(new Empty(this.row, this.column));
            } else {
                Map.getMap().setTileObject(this.originalTileObject);
            }
        }
    }
}
