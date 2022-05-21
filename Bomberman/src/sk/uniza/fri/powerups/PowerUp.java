package sk.uniza.fri.powerups;

import sk.uniza.fri.Map;
import sk.uniza.fri.ResourceCollection;
import sk.uniza.fri.characters.Bomber;
import sk.uniza.fri.characters.Mob;
import sk.uniza.fri.tiles.Explosion;
import sk.uniza.fri.tiles.TileObject;
import sk.uniza.fri.tiles.blocks.Empty;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class PowerUp extends TileObject {

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

        private BufferedImage texture;

        Type(ResourceCollection.Textures texture) {
            this.texture = texture.getTexture();
        }

        public BufferedImage getTexture() {
            return this.texture;
        }

        public abstract void activate(Bomber bomber);
    }

    private Type type;

    public PowerUp(int row, int column) {
        super(row, column);
        this.isWalkable = true;
        this.generateRandom();
        this.setTexture(this.type.getTexture());
    }

    public void generateRandom() {
        Random random = new Random();
        this.type = Type.values()[random.nextInt((Type.values().length))];
    }

    @Override
    public void handleCollision(Mob mob) {
        this.destroy();
    }

    @Override
    public void handleCollision(Bomber bomber) {
        this.destroy();
        this.type.activate(bomber);
    }

    @Override
    public void handleCollision(PowerUp powerUp) {

    }

    @Override
    public void handleCollision(Explosion explosion) {
        this.destroy();

        Map.getMap().setTileObject(explosion);
        explosion.showTexture();
    }

    @Override
    public void destroy() {
        this.hideTexture();
        Map.getMap().setTileObject(new Empty(this.getRow(), this.getColumn()));
    }
}
