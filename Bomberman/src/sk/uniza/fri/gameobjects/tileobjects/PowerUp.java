package sk.uniza.fri.gameobjects.tileobjects;

import sk.uniza.fri.Map;
import sk.uniza.fri.gui.ResourceCollection;
import sk.uniza.fri.gameobjects.characters.Bomber;
import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gameobjects.tileobjects.blocks.Empty;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Class for power up logic.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class PowerUp extends TileObject {

    /**
     * Enum for power ups types.
     */
    public enum Type {
        INCREASE_EXPLOSION(ResourceCollection.Textures.INCREASE_EXPLOSION) {
            @Override
            public void activate(Bomber bomber) {
                bomber.increaseExplosionRadius();
            }
        },
        ADD_BOMB(ResourceCollection.Textures.ADD_BOMB) {
            @Override
            public void activate(Bomber bomber) {
                bomber.addBomb();
            }
        };

        private final BufferedImage texture;

        /**
         * Creates power up with given texture.
         * @param texture of power up
         */
        Type(ResourceCollection.Textures texture) {
            this.texture = texture.getTexture();
        }

        /**
         * Returns power up texture.
         * @return texture
         */
        public BufferedImage getTexture() {
            return this.texture;
        }

        public abstract void activate(Bomber bomber);
    }

    private Type type;

    /**
     * Creates new power up at given coordinates.
     * @param row positionY
     * @param column positionX
     */
    public PowerUp(int row, int column) {
        super(row, column);
        this.isWalkable = true;
        this.generateRandom();
        this.setTexture(this.type.getTexture());
    }

    /**
     * Generates random power up from enum of power up types.
     */
    public void generateRandom() {
        Random random = new Random();
        this.type = Type.values()[random.nextInt((Type.values().length))];
    }

    /**
     * Destroys power up, if in collision with mob.
     * @param mob colliding mob
     */
    @Override
    public void handleCollision(Mob mob) {
        this.destroy();
    }

    /**
     * Destroys and activates power up, if in collision with bomber.
     * @param bomber colliding bomber
     */
    @Override
    public void handleCollision(Bomber bomber) {
        this.destroy();
        this.type.activate(bomber);
    }

    /**
     * Destroys and sets at current tile explosion, if in collision with explosion.
     * @param explosion colliding explosion
     */
    @Override
    public void handleCollision(Explosion explosion) {
        this.destroy();

        Map.getMap().setTileObject(explosion);
        explosion.showTexture();
    }

    /**
     * Destroys current power up and creates empty tile at current position.
     */
    @Override
    public void destroy() {
        this.hideTexture();
        Map.getMap().setTileObject(new Empty(this.getRow(), this.getColumn()));
    }
}
