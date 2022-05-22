package sk.uniza.fri.gameobjects.tileobjects;

import sk.uniza.fri.Bomberman;
import sk.uniza.fri.Manazer;
import sk.uniza.fri.gameobjects.characters.Bomber;
import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gameobjects.characters.Direction;
import sk.uniza.fri.Map;
import sk.uniza.fri.gui.ResourceCollection;
import sk.uniza.fri.gameobjects.tileobjects.blocks.Empty;
import sk.uniza.fri.gameobjects.tileobjects.blocks.UnbreakableWall;

/**
 * Class, that manages logic of Explosion.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class Explosion extends TileObject {

    private static final int TIME_FOR_EXPLOSION = 8;

    private TileObject originalTileObject;

    private Direction direction;
    private int explosionsLeft;

    private int tickCount;
    private Manazer manazer;

    /**
     * Creates new explosion with given parameters.
     * @param direction to which explosion must expand
     * @param row position
     * @param column position
     * @param explosionsLeft count of how many explosions is left
     */
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

    /**
     * Method, which is called every x (set in class Manazer, current 0.25 seconds) nanosecond by Manazer.
     */
    public void tik() {
        this.tickCount++;
        if (this.tickCount >= TIME_FOR_EXPLOSION) {
            this.destroy();
        }
    }

    /**
     * Creates new explosion based on this current explosion parameters.
     */
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

    /**
     * Sets texture and rotation based on current parameters.
     */
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

    /**
     * Sets how many explosions left.
     * @param explosionsLeft
     */
    public void setExplosionsLeft(int explosionsLeft) {
        this.explosionsLeft = explosionsLeft;
    }

    /**
     * Takes mob life if in collision with it.
     * @param mob colliding mob
     */
    @Override
    public void handleCollision(Mob mob) {
        mob.takeLife();
    }

    /**
     * Takes bomber life if in collision with it.
     * @param bomber colliding bomber
     */
    @Override
    public void handleCollision(Bomber bomber) {
        bomber.takeLife();
    }

    /**
     * Sets new explosion texture if in collision with it.
     * @param explosion colliding explosion
     */
    @Override
    public void handleCollision(Explosion explosion) {
        if ((explosion.direction == Direction.LEFT || explosion.direction == Direction.RIGHT) && (this.direction == Direction.LEFT || this.direction == Direction.RIGHT)) {
            if (this.originalTileObject instanceof Marker marker) {
                if (marker.getMarkedTile() instanceof Bomb) {
                    this.setTexture(ResourceCollection.Textures.EXPLOSION_START_BIG.getTexture());
                    return;
                }
            }

            if (Map.getMap().getTileObjects()[this.row + explosion.direction.getRow()][this.column + explosion.direction.getColumn()] instanceof UnbreakableWall) {
                this.setTexture(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture());
            } else {
                this.setTexture(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture());
            }
        } else if ((explosion.direction == Direction.UP || explosion.direction == Direction.DOWN) && (this.direction == Direction.UP || this.direction == Direction.DOWN)) {
            if (this.originalTileObject instanceof Marker marker) {
                if (marker.getMarkedTile() instanceof Bomb) {
                    this.setTexture(ResourceCollection.Textures.EXPLOSION_START_BIG.getTexture());
                    return;
                }
            }

            if (Map.getMap().getTileObjects()[this.row + explosion.direction.getRow()][this.column + explosion.direction.getColumn()] instanceof UnbreakableWall) {
                this.setTexture(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture());
            } else {
                this.setTexture(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture());
            }
        } else {
            this.setTexture(ResourceCollection.Textures.EXPLOSION_START_BIG.getTexture());
        }
    }

    /**
     * Destroys explosion and creates at this tile tile, which was there before explosion or if there was not any, creates new empty tile.
     */
    @Override
    public void destroy() {
        this.hideTexture();
        this.explosionsLeft = 0;
        this.manazer.prestanSpravovatObjekt(this);

        // Skontrolujem, či medzitým tu neprišla nová explózia. Ak áno, tak túto aktuálnu explóziu iba zničím a nezobrazím namiesto nej iný Tile.
        if (Map.getMap().getTileObjects()[this.row][this.column] == this) {
            if (this.originalTileObject == null || this.originalTileObject instanceof Marker) {
                Map.getMap().setTileObject(new Empty(this.row, this.column));
            } else {
                Map.getMap().setTileObject(this.originalTileObject);
            }
        }
    }
}
