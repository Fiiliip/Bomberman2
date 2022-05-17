package sk.uniza.fri.characters.enemies;

import sk.uniza.fri.characters.Character;
import sk.uniza.fri.ResourceCollection;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Enemy extends Character {

    public Enemy(String fileWithTexture, int x, int y) {
        super(ResourceCollection.loadBufferedImage(null), x, y);
    }

    @Override
    public void destroy() {

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
}
