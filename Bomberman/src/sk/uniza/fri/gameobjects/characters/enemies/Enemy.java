package sk.uniza.fri.gameobjects.characters.enemies;

import sk.uniza.fri.gameobjects.characters.Mob;

import java.awt.image.BufferedImage;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Enemy extends Mob {

    public Enemy(BufferedImage texture, int x, int y) {
        super(texture, x, y);
    }

    @Override
    public int getCountOfSpecialAbility() {
        return 0;
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void action() {

    }

    @Override
    public void destroy() {

    }
}
