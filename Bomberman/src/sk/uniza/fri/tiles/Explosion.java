package sk.uniza.fri.tiles;

import sk.uniza.fri.characters.Character;
import sk.uniza.fri.characters.Direction;
import sk.uniza.fri.Map;
import sk.uniza.fri.powerups.PowerUp;
import sk.uniza.fri.ResourceCollection;
import sk.uniza.fri.tiles.blocks.UnbreakableWall;

import java.awt.image.BufferedImage;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Explosion extends TileObject {

    private int row;
    private int column;
    private Direction direction;
    private int explosionsLeft;

    public Explosion(BufferedImage texture, int row, int column, Direction direction, int explosionsLeft) {
        super(texture, row, column);

        this.row = row;
        this.column = column;
        this.direction = direction;
        this.explosionsLeft = explosionsLeft - 1;

        this.setRotation();

        Map.getMap().getTileObjects()[row][column].handleCollision(this);

        if (this.explosionsLeft <= 0) {
            return;
        }

        this.createNextExplosion();

        if (direction != Direction.NONE) {
            if (this.explosionsLeft == 1) {

            } else {

            }
        } else {
            new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), row + Direction.UP.getRow(), column + Direction.UP.getColumn(), Direction.UP, this.explosionsLeft);
            new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), row + Direction.DOWN.getRow(), column + Direction.DOWN.getColumn(), Direction.DOWN, this.explosionsLeft);
            new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), row + Direction.LEFT.getRow(), column + Direction.LEFT.getColumn(), Direction.LEFT, this.explosionsLeft);
            new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), row + Direction.RIGHT.getRow(), column + Direction.RIGHT.getColumn(), Direction.RIGHT, this.explosionsLeft);
        }
    }

    public void createNextExplosion() {
        if (this.direction != Direction.NONE) {
            if (this.explosionsLeft == 1) {
                new Explosion(ResourceCollection.Textures.EXPLOSION_END_BIG.getTexture(), this.row + this.direction.getRow(), this.column + this.direction.getColumn(), this.direction, this.explosionsLeft);
            } else {
                if (Map.getMap().getTileObjects()[this.row + this.direction.getRow()][this.column + this.direction.getColumn()] instanceof UnbreakableWall) {
                    new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), this.row + this.direction.getRow(), this.column + this.direction.getColumn(), this.direction, this.explosionsLeft);
                }
            }
        } else {
            if (!(Map.getMap().getTileObjects()[this.row + Direction.UP.getRow()][this.column + Direction.UP.getColumn()] instanceof UnbreakableWall)) {
                new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), this.row + Direction.UP.getRow(), this.column + Direction.UP.getColumn(), Direction.UP, this.explosionsLeft);
            }

            if (!(Map.getMap().getTileObjects()[this.row + Direction.DOWN.getRow()][this.column + Direction.DOWN.getColumn()] instanceof UnbreakableWall)) {
                new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), this.row + Direction.DOWN.getRow(), this.column + Direction.DOWN.getColumn(), Direction.DOWN, this.explosionsLeft);
            }

            if (!(Map.getMap().getTileObjects()[this.row + Direction.LEFT.getRow()][this.column + Direction.LEFT.getColumn()] instanceof UnbreakableWall)) {
                new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), this.row + Direction.LEFT.getRow(), this.column + Direction.LEFT.getColumn(), Direction.LEFT, this.explosionsLeft);
            }

            if (!(Map.getMap().getTileObjects()[this.row + Direction.RIGHT.getRow()][this.column + Direction.RIGHT.getColumn()] instanceof UnbreakableWall)) {
                new Explosion(ResourceCollection.Textures.EXPLOSION_MIDDLE_BIG.getTexture(), this.row + Direction.RIGHT.getRow(), this.column + Direction.RIGHT.getColumn(), Direction.RIGHT, this.explosionsLeft);
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
        this.setRotation();
    }
}
