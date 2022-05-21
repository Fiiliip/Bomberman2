package sk.uniza.fri.powerups;

import sk.uniza.fri.Map;
import sk.uniza.fri.characters.Bomber;
import sk.uniza.fri.characters.Mob;
import sk.uniza.fri.tiles.Explosion;
import sk.uniza.fri.tiles.blocks.Empty;

import java.awt.image.BufferedImage;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class BombUp extends PowerUp {

    public BombUp(BufferedImage texture, int row, int column) {
        super(texture, row, column);
    }

    @Override
    public void activate(Mob mob) {
        this.destroy();
        mob.setCountOfSpecialAbility(mob.getCountOfSpecialAbility() + 1);
        mob.getMobHUD().updateValues();
    }

    @Override
    public void handleCollision(Mob mob) {
        this.activate(mob);
    }

    @Override
    public void handleCollision(PowerUp powerUp) {

    }

    @Override
    public void handleCollision(Explosion explosion) {
        this.destroy();

        // Nastavím tu späť explóziu, ktorá zničila tento PowerUp a zobrazím ju.
        Map.getMap().setTileObject(explosion);
        explosion.showTexture();
    }

    @Override
    public void destroy() {
        this.hideTexture();
        Map.getMap().setTileObject(new Empty(this.getRow(), this.getColumn()));
    }
}
