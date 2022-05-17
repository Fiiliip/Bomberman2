package sk.uniza.fri.tiles;

import sk.uniza.fri.Manazer;
import sk.uniza.fri.characters.Character;
import sk.uniza.fri.characters.Direction;
import sk.uniza.fri.Map;
import sk.uniza.fri.powerups.PowerUp;
import sk.uniza.fri.ResourceCollection;
import sk.uniza.fri.tiles.blocks.Empty;
import sk.uniza.fri.tiles.blocks.UnbreakableWall;

import java.awt.image.BufferedImage;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Explosion extends TileObject {

    private static final int TIME_FOR_EXPLOSION = 8;

    private int row;
    private int column;
    private Direction direction;
    private int explosionsLeft;

    private Manazer manazer;
    private int tickCount;

    public Explosion(BufferedImage texture, int row, int column, Direction direction, int explosionsLeft) {
        super(texture, row, column);

        this.row = row;
        this.column = column;
        this.direction = direction;
        this.explosionsLeft = explosionsLeft - 1;

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

        this.setRotation();

        Map.getMap().getTileObjects()[this.row][this.column].handleCollision(this);
//        Map.getMap().setTileObject(this);

        if (this.explosionsLeft > 0) {
            this.createNextExplosion();
        }
    }

    public void tik() {
        this.tickCount++;
        if (this.tickCount >= TIME_FOR_EXPLOSION) {
            this.destroy();
        }
    }

    public void createNextExplosion() {
        if (this.direction != Direction.NONE) {
            if (this.explosionsLeft == 1) {
                if (!(Map.getMap().getTileObjects()[this.row + this.direction.getRow()][this.column + this.direction.getColumn()] instanceof UnbreakableWall)) {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture(), this.row + this.direction.getRow(), this.column + this.direction.getColumn(), this.direction, this.explosionsLeft);
                }
            } else {
                if (Map.getMap().getTileObjects()[this.row + this.direction.getRow() * 2][this.column + this.direction.getColumn() * 2] instanceof UnbreakableWall) {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture(), this.row + this.direction.getRow(), this.column + this.direction.getColumn(), this.direction, this.explosionsLeft);
                } else if (!(Map.getMap().getTileObjects()[this.row + this.direction.getRow()][this.column + this.direction.getColumn()] instanceof UnbreakableWall)) {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), this.row + this.direction.getRow(), this.column + this.direction.getColumn(), this.direction, this.explosionsLeft);
                }
            }
        } else {
            if (!(Map.getMap().getTileObjects()[this.row + Direction.UP.getRow()][this.column + Direction.UP.getColumn()] instanceof UnbreakableWall)) {
                if (Map.getMap().getTileObjects()[this.row + Direction.UP.getRow() * 2][this.column + Direction.UP.getColumn() * 2] instanceof UnbreakableWall) {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture(), this.row + Direction.UP.getRow(), this.column + Direction.UP.getColumn(), Direction.UP, this.explosionsLeft);
                } else {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), this.row + Direction.UP.getRow(), this.column + Direction.UP.getColumn(), Direction.UP, this.explosionsLeft);
                }
            }

            if (!(Map.getMap().getTileObjects()[this.row + Direction.DOWN.getRow()][this.column + Direction.DOWN.getColumn()] instanceof UnbreakableWall)) {
                if (Map.getMap().getTileObjects()[this.row + Direction.DOWN.getRow() * 2][this.column + Direction.DOWN.getColumn() * 2] instanceof UnbreakableWall) {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture(), this.row + Direction.DOWN.getRow(), this.column + Direction.DOWN.getColumn(), Direction.DOWN, this.explosionsLeft);
                } else {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), this.row + Direction.DOWN.getRow(), this.column + Direction.DOWN.getColumn(), Direction.DOWN, this.explosionsLeft);
                }
            }

            if (!(Map.getMap().getTileObjects()[this.row + Direction.LEFT.getRow()][this.column + Direction.LEFT.getColumn()] instanceof UnbreakableWall)) {
                if (Map.getMap().getTileObjects()[this.row + Direction.LEFT.getRow() * 2][this.column + Direction.LEFT.getColumn() * 2] instanceof UnbreakableWall) {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture(), this.row + Direction.LEFT.getRow(), this.column + Direction.LEFT.getColumn(), Direction.LEFT, this.explosionsLeft);
                } else {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), this.row + Direction.LEFT.getRow(), this.column + Direction.LEFT.getColumn(), Direction.LEFT, this.explosionsLeft);
                }
            }

            if (!(Map.getMap().getTileObjects()[this.row + Direction.RIGHT.getRow()][this.column + Direction.RIGHT.getColumn()] instanceof UnbreakableWall)) {
                if (Map.getMap().getTileObjects()[this.row + Direction.RIGHT.getRow() * 2][this.column + Direction.RIGHT.getColumn() * 2] instanceof UnbreakableWall) {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture(), this.row + Direction.RIGHT.getRow(), this.column + Direction.RIGHT.getColumn(), Direction.RIGHT, this.explosionsLeft);
                } else {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), this.row + Direction.RIGHT.getRow(), this.column + Direction.RIGHT.getColumn(), Direction.RIGHT, this.explosionsLeft);
                }
            }
        }
    }

    public void setRotation() {
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
    public void handleCollision(Character character) {
        character.takeLife();
    }

    @Override
    public void handleCollision(PowerUp powerUp) {

    }

    @Override
    public void handleCollision(Explosion explosion) {

    }

    @Override
    public void destroy() {
        this.explosionsLeft = 0;
        this.hideTexture();
        this.manazer.prestanSpravovatObjekt(this);
        Map.getMap().setTileObject(new Empty(this.row, this.column));
    }
}
